package com.example.miniecommerce.web.controller;

import com.example.miniecommerce.service.CarrinhoService;
import com.example.miniecommerce.web.dto.Carrinho.in.CarrinhoCreateRequestDto;
import com.example.miniecommerce.web.dto.Carrinho.out.CarrinhoResponseDetailDto;
import jakarta.validation.Valid;
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
    public CarrinhoResponseDetailDto carrinho(UriComponentsBuilder uriBuilder, @Valid CarrinhoCreateRequestDto dto){

        return new CarrinhoResponseDetailDto();
    }


}
