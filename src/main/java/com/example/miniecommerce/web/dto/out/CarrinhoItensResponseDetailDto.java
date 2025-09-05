package com.example.miniecommerce.web.dto.out;

import java.time.LocalDateTime;
import java.util.List;

public record CarrinhoItensResponseDetailDto(Long id,
                                             LocalDateTime dataCadastro,
                                             LocalDateTime dataAtualizacao,
                                             List<ItemCarrinhoResponseDto> itens) {
}
