package com.example.miniecommerce.web.dto.out;

import java.math.BigDecimal;

public record ProdutoPedidoResponseDto(
        String imgUrl,
        String nome,
        BigDecimal preco,
        int quantidade,
        BigDecimal total,
        BigDecimal desconto,
        BigDecimal subtotal,
        BigDecimal frete

        )
{
}
