package com.example.miniecommerce.web.controller;

import com.example.miniecommerce.service.CrudProdutoService;
import com.example.miniecommerce.web.dto.Produto.in.ProdutoCreateRequestDto;
import com.example.miniecommerce.web.dto.Produto.out.ProdutoDetailsResponseDto;
import com.example.miniecommerce.web.dto.Usuario.out.ListaUsuarioResponseDto;
import com.example.miniecommerce.web.dto.Usuario.out.UsuarioDetailsResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    private final CrudProdutoService crudProdutoService;

    public ProdutoController(CrudProdutoService crudProdutoService) {
        this.crudProdutoService = crudProdutoService;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDetailsResponseDto> create(@RequestBody @Valid ProdutoCreateRequestDto dto, UriComponentsBuilder uriBuilder) {

        ProdutoDetailsResponseDto cadastroProduto = crudProdutoService.cadastrar(dto);
        var uri = uriBuilder.path("/produto/{id}").buildAndExpand(cadastroProduto.id()).toUri();
        return ResponseEntity.created(uri).body(cadastroProduto);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDetailsResponseDto>> listar(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {

        var page = crudProdutoService.listar(paginacao);

        return ResponseEntity.ok(page);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoDetailsResponseDto> delete(@PathVariable Long id) {
        ProdutoDetailsResponseDto produtoDeletado = crudProdutoService.deletar(id);
        return ResponseEntity.ok(produtoDeletado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDetailsResponseDto> detalhar(@PathVariable Long id) {
        ProdutoDetailsResponseDto  dto = crudProdutoService.detalhar(id);
        return ResponseEntity.ok(dto);
    }




}
