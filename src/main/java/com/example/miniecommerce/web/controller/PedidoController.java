package com.example.miniecommerce.web.controller;

import com.example.miniecommerce.domain.Usuario;
import com.example.miniecommerce.service.PagamentoService;
import com.example.miniecommerce.service.PedidoServices.PedidoEnderecoService;
import com.example.miniecommerce.service.PedidoServices.PedidoEnviarService;
import com.example.miniecommerce.service.PedidoServices.PedidoFreteService;
import com.example.miniecommerce.service.PedidoServices.PedidoCreateService;
import com.example.miniecommerce.service.RealizarPagamento;
import com.example.miniecommerce.web.dto.in.EnderecoDto;
import com.example.miniecommerce.web.dto.in.FreteDto;
import com.example.miniecommerce.web.dto.in.PagamentoCreateDto;
import com.example.miniecommerce.web.dto.in.PedidoEnviarRequestDto;
import com.example.miniecommerce.web.dto.out.PedidoDetailsResponseDto;

import com.example.miniecommerce.web.dto.out.PedidoStatusEnderecoDto;
import com.example.miniecommerce.web.dto.out.PedidoStatusFrete;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pedido")
@Tag(name = "Pedido", description = "Gerenciamento do pedidos")
public class PedidoController {


    @Autowired
    private PedidoCreateService pedidoCreateService;

    @Autowired
    private PedidoEnviarService pedidoEnviarService;

    @Autowired
    private PedidoEnderecoService pedidoEnderecoService;

    @Autowired
    PedidoFreteService pedidoFreteService;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private RealizarPagamento realizarPagamento;

    @PostMapping("")
    private ResponseEntity<PedidoDetailsResponseDto> salvar(
                                                            UriComponentsBuilder uriBuilder,
                                                            @AuthenticationPrincipal Usuario usuario){

        PedidoDetailsResponseDto cadastrarNovoPedido = pedidoCreateService.salvar(usuario);
        var uri = uriBuilder.path("/pedido/{id}").buildAndExpand(cadastrarNovoPedido.id()).toUri();
        return ResponseEntity.created(uri).body(cadastrarNovoPedido);

    }

    @PatchMapping("/{id}/endereco")
    private ResponseEntity atualizarEndereco(@RequestBody EnderecoDto enderecoDto, @PathVariable Long id){
        PedidoStatusEnderecoDto pedidoStatusDto = pedidoEnderecoService.atualizar(id,enderecoDto);
        return ResponseEntity.ok(pedidoStatusDto);
    };

    @PatchMapping("/{idPedido}/frete")
    private ResponseEntity atualizarFrete(@PathVariable Long idPedido,@RequestBody FreteDto freteDto){

       PedidoStatusFrete pedidoStatusFrete =  pedidoFreteService.atualizarFrete(idPedido,freteDto);
       return ResponseEntity.ok(pedidoStatusFrete);
    }

    @PatchMapping("/{idPedido}/pagamento")
    private ResponseEntity<PedidoDetailsResponseDto> atualizarPagamento(@PathVariable Long idPedido, PagamentoCreateDto pagamentoDto){
        pagamentoService.atualizarPagamento(idPedido,pagamentoDto);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idPedido}/pagamento/checkout")
    private ResponseEntity checkout(@PathVariable Long idPedido) throws StripeException {
        realizarPagamento.realizarPagamento(idPedido);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/{id}/enviar")
    public ResponseEntity enviarPedido(@PathVariable Long idPedido,PedidoEnviarRequestDto pedidoEnviarRequestDto){

            pedidoEnviarService.enviarPedido(idPedido, pedidoEnviarRequestDto);

            return ResponseEntity.ok().build();

    }



}
