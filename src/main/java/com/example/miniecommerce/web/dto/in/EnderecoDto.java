package com.example.miniecommerce.web.dto.in;

public record EnderecoDto(
        String rua,
        String numero,
        String cidade,
        String estado,
        String cep
) {}