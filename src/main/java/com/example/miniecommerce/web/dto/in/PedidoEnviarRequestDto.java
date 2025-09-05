package com.example.miniecommerce.web.dto.in;


public record PedidoEnviarRequestDto(
        String destinatarioNome,
        String destinatarioEndereco,
        String destinatarioCep
) {}
