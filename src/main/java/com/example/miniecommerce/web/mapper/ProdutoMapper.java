package com.example.miniecommerce.web.mapper;

import com.example.miniecommerce.domain.Produto;
import com.example.miniecommerce.web.dto.Produto.in.ProdutoCreateRequestDto;
import com.example.miniecommerce.web.dto.Produto.out.ProdutoDetailsResponseDto;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    Produto toProduto(@Valid ProdutoCreateRequestDto dto);

    ProdutoDetailsResponseDto toResponseProdutoDetailDto(Produto produto);
}
