package com.example.miniecommerce.web.controller;

import com.example.miniecommerce.domain.Usuario;
import com.example.miniecommerce.service.CarrinhoService;
import com.example.miniecommerce.web.dto.Carrinho.in.CarrinhoCreateRequestDto;
import com.example.miniecommerce.web.dto.Carrinho.out.CarrinhoResponseDetailDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {


    private final CarrinhoService carrinhoService;
    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }




    @PostMapping
    @Transactional
    public ResponseEntity<CarrinhoResponseDetailDto> carrinho(UriComponentsBuilder
                                                          uriBuilder, @Valid CarrinhoCreateRequestDto dto,
                                                              @AuthenticationPrincipal Usuario usuario){
        CarrinhoResponseDetailDto cadastroCarrinho = carrinhoService.cadastrar(usuario);

        var uri = uriBuilder.path("/carrinho/{id}").buildAndExpand(cadastroCarrinho.id()).toUri();
        return ResponseEntity.created(uri).body(cadastroCarrinho);

    }


}
