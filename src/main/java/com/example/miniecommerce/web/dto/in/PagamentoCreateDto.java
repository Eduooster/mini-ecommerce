package com.example.miniecommerce.web.dto.in;

import com.example.miniecommerce.web.dto.PedidoDto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public record PagamentoCreateDto(
        LocalDateTime dataPagamento,
        String metodoPagamento,
        BigDecimal valorTotal,
        PedidoDto pedidoDto

        ) {
}
