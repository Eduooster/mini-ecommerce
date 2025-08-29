package com.example.miniecommerce.web.dto.out;

import java.math.BigDecimal;

public record ItemCarrinhoResponseDto(
        Long produtoId,
        String nomeProduto,
        int quantidade,
        BigDecimal precoUnitario,
        BigDecimal subtotal
) {}
