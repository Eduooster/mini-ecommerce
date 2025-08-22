package com.example.miniecommerce.web.mapper;


import com.example.miniecommerce.domain.Carrinho;
import com.example.miniecommerce.web.dto.Carrinho.in.CarrinhoCreateRequestDto;
import com.example.miniecommerce.web.dto.Carrinho.out.CarrinhoResponseDetailDto;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CarrinhoMapper {
    Carrinho toDomain(@Valid CarrinhoCreateRequestDto dto);

    CarrinhoResponseDetailDto toCarrinhoResponseDetailDto(Carrinho carrinho);


}
