package com.example.miniecommerce.web.dto.out;

// RespostaEtiquetaDto.java
public record RespostaEtiquetaDto(
        String shipmentId,
        String status,
        String labelUrl
) {}
