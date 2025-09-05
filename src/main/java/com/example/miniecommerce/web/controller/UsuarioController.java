package com.example.miniecommerce.web.controller;

import com.example.miniecommerce.service.UsuarioServices.CrudUsuarioService;
import com.example.miniecommerce.web.dto.in.UsuarioCreateRequestDto;
import com.example.miniecommerce.web.dto.out.ListaUsuarioResponseDto;
import com.example.miniecommerce.web.dto.out.UsuarioDetailsResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "Gerenciamento de usuarios")
public class UsuarioController {


    private final CrudUsuarioService crudUsuarioService;


    public UsuarioController(CrudUsuarioService crudUsuarioService) {
        this.crudUsuarioService = crudUsuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDetailsResponseDto> create(@RequestBody @Valid UsuarioCreateRequestDto dto, UriComponentsBuilder uriBuilder) {

        UsuarioDetailsResponseDto cadastroNovoUsuario = crudUsuarioService.cadastrar(dto);
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(cadastroNovoUsuario.id()).toUri();
        return ResponseEntity.created(uri).body(cadastroNovoUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<ListaUsuarioResponseDto>> listar(
            @PageableDefault(size = 10, sort = { "username" }) Pageable paginacao) {

        var page = crudUsuarioService.listar(paginacao);

        return ResponseEntity.ok(page);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDetailsResponseDto> delete(@PathVariable Long id) {
        UsuarioDetailsResponseDto usuarioDeletado = crudUsuarioService.deletar(id);
        return ResponseEntity.ok(usuarioDeletado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDetailsResponseDto> detalhar(@PathVariable Long id) {
        UsuarioDetailsResponseDto  dto = crudUsuarioService.detalhar(id);
        return ResponseEntity.ok(dto);
    }



}
