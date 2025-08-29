package com.example.miniecommerce.web.mapper;


import com.example.miniecommerce.domain.Carrinho;
import com.example.miniecommerce.domain.ItemCarrinho;
import com.example.miniecommerce.web.dto.in.CarrinhoCreateRequestDto;
import com.example.miniecommerce.web.dto.out.CarrinhoResponseDetailDto;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarrinhoMapper {
    Carrinho toDomain(@Valid CarrinhoCreateRequestDto dto);

    CarrinhoResponseDetailDto toCarrinhoResponseDetailDto(Carrinho carrinho);


    CarrinhoResponseDetailDto toCarrinhoListResponseDetailDto(Carrinho carrinho);
}
