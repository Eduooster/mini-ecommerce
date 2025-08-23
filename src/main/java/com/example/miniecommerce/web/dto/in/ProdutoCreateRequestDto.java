package com.example.miniecommerce.web.dto.in;

import java.math.BigDecimal;

public record ProdutoCreateRequestDto(
        String categoriaProduto,
        String nome,
        String descricao,
        BigDecimal preco,
        String sku,
        String urlImagem
) {
}
