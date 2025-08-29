package com.example.miniecommerce.web.dto.out;

import com.example.miniecommerce.domain.Produto;

public record ItemListDto(Produto produto, Long quantidade) {
}
