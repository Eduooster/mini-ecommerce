package com.example.miniecommerce.web.controller;

import com.example.miniecommerce.domain.Pedido;
import com.example.miniecommerce.infra.repositorie.PedidoRepository;
import com.example.miniecommerce.service.PedidoCreateService;
import com.example.miniecommerce.web.dto.in.PedidoCreateRequestDto;
import com.example.miniecommerce.web.dto.out.PedidoDetailsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class PedidoController {


    @Autowired
    private PedidoCreateService pedidoCreateService;

    @PostMapping
    private ResponseEntity<PedidoDetailsResponseDto> salvar(@RequestBody PedidoCreateRequestDto pedido,
                                                            UriComponentsBuilder uriBuilder){

        PedidoDetailsResponseDto cadastrarNovoPedido = pedidoCreateService.salvar(pedido);
        var uri = uriBuilder.path("/pedido/{id}").buildAndExpand(cadastrarNovoPedido.id()).toUri();
        return ResponseEntity.created(uri).body(cadastrarNovoPedido);




    }
}
