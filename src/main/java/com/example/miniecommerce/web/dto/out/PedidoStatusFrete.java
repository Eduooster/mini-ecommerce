package com.example.miniecommerce.web.dto.out;

import com.example.miniecommerce.domain.StatusPedido;
import com.example.miniecommerce.web.dto.in.FreteDto;

public record PedidoStatusFrete(FreteDto freteDto, StatusPedido status) {
}
