package com.example.miniecommerce.web.dto.out;

import com.example.miniecommerce.domain.CategoriaProduto;

import java.math.BigDecimal;

public record ProdutoDetailsResponseDto(
        Long id,
        CategoriaProduto categoriaProduto,
        String nome,
        String descricao,
        BigDecimal preco,
        String urlImagem) {
}
