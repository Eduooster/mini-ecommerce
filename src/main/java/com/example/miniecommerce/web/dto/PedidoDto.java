package com.example.miniecommerce.web.dto;

import com.example.miniecommerce.domain.StatusPedido;

import com.example.miniecommerce.web.dto.in.ItemCarirrihoDto;

import java.util.List;

// PedidoDto.java
public record PedidoDto(
        Long idPedido,
        List<ItemCarirrihoDto> itens,
        StatusPedido statusPedido
) {}
