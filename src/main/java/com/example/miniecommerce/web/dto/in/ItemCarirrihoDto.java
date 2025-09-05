package com.example.miniecommerce.web.dto.in;

import java.math.BigDecimal;

public record ItemCarirrihoDto(
        String produtoId,
        String nome,
        Long quantidade,
        BigDecimal precoUnitario
) {
}
