package com.example.miniecommerce.web.dto.out;

import com.example.miniecommerce.domain.CategoriaProduto;

public record ProdutoDetailsResponseDto(Long id, CategoriaProduto categoriaProduto, String nome, String descricao,int estoque) {
}
