package com.example.miniecommerce.web.dto.out;

import com.example.miniecommerce.domain.StatusPedido;
import com.example.miniecommerce.web.dto.in.EnderecoDto;

public record PedidoStatusEnderecoDto(EnderecoDto enderecoDto, StatusPedido statusPedido) {
}
