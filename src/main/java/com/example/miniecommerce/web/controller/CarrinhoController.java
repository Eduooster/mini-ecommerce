package com.example.miniecommerce.web.controller;

import com.example.miniecommerce.domain.Usuario;
import com.example.miniecommerce.service.CarrinhoServices.AdicionarOuRemoverItem;
import com.example.miniecommerce.service.CarrinhoServices.AtualizarCarrinhoService;
import com.example.miniecommerce.service.CarrinhoServices.CadastrarCarrinhoService;
import com.example.miniecommerce.service.CarrinhoServices.ListarCarrinho;
import com.example.miniecommerce.web.dto.in.ItemCarrinhoDto;
import com.example.miniecommerce.web.dto.in.ItemCarrinhoRequestDto;
import com.example.miniecommerce.web.dto.out.CarrinhoItensResponseDetailDto;
import com.example.miniecommerce.web.dto.out.CarrinhoResponseDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/carrinho")
@Tag(name = "Carrinho", description = "Gerenciamento do carrinho de compras")
public class CarrinhoController {


    private final CadastrarCarrinhoService carrinhoService;
    private final AtualizarCarrinhoService atualizarItensCarrinho;
    private final AdicionarOuRemoverItem adicionarOuRemoverItem;
    private final ListarCarrinho listarCarrinho;
    public CarrinhoController(CadastrarCarrinhoService carrinhoService, AtualizarCarrinhoService atualizarCarrinho, AdicionarOuRemoverItem adicionarOuRemoverItem,ListarCarrinho listarCarrinho) {
        this.carrinhoService = carrinhoService;
        this.atualizarItensCarrinho = atualizarCarrinho;
        this.adicionarOuRemoverItem = adicionarOuRemoverItem;
        this.listarCarrinho =listarCarrinho;
    }





    @Operation(summary = "Criar carrinho", description = "Cria um novo carrinho para o usuário logado (se não existir um ativo).")
    @PostMapping
    @Transactional
    public ResponseEntity<CarrinhoResponseDetailDto> criarCarrinho(
            UriComponentsBuilder uriBuilder,
            @AuthenticationPrincipal Usuario usuario) {

        CarrinhoResponseDetailDto cadastroCarrinho = carrinhoService.cadastrar(usuario);

        var uri = uriBuilder.path("/carrinhos/{id}")
                .buildAndExpand(cadastroCarrinho.id())
                .toUri();

        return ResponseEntity.created(uri).body(cadastroCarrinho);
    }


    @Operation(summary = "Listar carrinhos", description = "Lista todos os carrinhos. Paginação suportada.")
    @GetMapping
    public ResponseEntity<Page<CarrinhoResponseDetailDto>> listar(
            @PageableDefault(size = 10) Pageable paginacao) {

        var page = listarCarrinho.listarCarrinhos(paginacao);
        return ResponseEntity.ok(page);
    }


    @Operation(summary = "Mostrar carrinho", description = "Retorna os detalhes de um carrinho pelo ID.")
    @GetMapping("/{idCarrinho}")
    public ResponseEntity<CarrinhoResponseDetailDto> mostrarCarrinho(
            @PathVariable Long idCarrinho) {

        CarrinhoResponseDetailDto carrinho = listarCarrinho.mostrarCarrinho(idCarrinho);
        return ResponseEntity.ok(carrinho);
    }


    @Operation(summary = "Adicionar ou atualizar itens", description = "Adiciona itens ao carrinho ou atualiza a quantidade se já existirem.")
    @PutMapping("/{idCarrinho}/itens")
    public ResponseEntity<CarrinhoItensResponseDetailDto> adicionarItens(
            @PathVariable Long idCarrinho,
            @RequestBody List<ItemCarrinhoDto> itensCarrinho,
            @AuthenticationPrincipal Usuario usuario) {

        CarrinhoItensResponseDetailDto response = atualizarItensCarrinho
                .atualizarItensCarrinho(usuario.getId(), itensCarrinho, idCarrinho);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Atualizar quantidade de item", description = "Altera a quantidade de um item específico dentro do carrinho.")
    @PutMapping("/{idCarrinho}/itens/{idItem}")
    public ResponseEntity<Void> modificarQuantidadeItem(
            @PathVariable Long idCarrinho,
            @PathVariable Long idItem,
            @RequestBody ItemCarrinhoRequestDto itemCarrinho,
            @AuthenticationPrincipal Usuario usuario) {

        adicionarOuRemoverItem.atualizarQuantidadeItem(idCarrinho, itemCarrinho, usuario.getId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idCarrinho}/checkout")
    private ResponseEntity<Void> checkout(){

    }

    // 6️⃣ Remover um item do carrinho
    /*
    @Operation(summary = "Remover item do carrinho", description = "Remove um item específico do carrinho.")
    @DeleteMapping("/{idCarrinho}/itens/{idItem}")
    public ResponseEntity<Void> removerItem(
            @PathVariable Long idCarrinho,
            @PathVariable Long idItem,
            @AuthenticationPrincipal Usuario usuario) {

        removerItemCarrinho.removerItem(usuario.getId(), idCarrinho, idItem);
        return ResponseEntity.noContent().build();
    }


     */







}
