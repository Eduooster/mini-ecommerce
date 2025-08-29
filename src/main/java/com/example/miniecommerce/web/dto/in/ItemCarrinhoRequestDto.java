package com.example.miniecommerce.web.dto.in;

import com.example.miniecommerce.domain.ItemCarrinho;
import jakarta.validation.constraints.Min;

public record ItemCarrinhoRequestDto(Long itemId,
                                     @Min(value = 1, message = "A quantidade deve ser maior que zero")Integer quantidade) {
}
