package com.example.miniecommerce.web.mapper;


import com.example.miniecommerce.domain.Carrinho;
import com.example.miniecommerce.domain.ItemCarrinho;

import com.example.miniecommerce.web.dto.out.CarrinhoResponseDetailDto;
import com.example.miniecommerce.web.dto.out.ItemCarrinhoResponseDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarrinhoMapper {



    CarrinhoResponseDetailDto toCarrinhoResponseDetailDto(Carrinho carrinho);

    List<ItemCarrinhoResponseDto> toItemCarrinhoResponseDto(List<ItemCarrinho> itens);



    @Mapping(target = "produtoId", source = "produto.id")
    @Mapping(target = "nomeProduto", source = "produto.nome")
    @Mapping(target = "precoUnitario", source = "produto.preco")
    @Mapping(target = "subtotal", expression = "java(item.getProduto().getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())))")
    ItemCarrinhoResponseDto toItemCarrinhoResponseDto(ItemCarrinho item);

}
