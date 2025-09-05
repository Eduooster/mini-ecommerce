package com.example.miniecommerce.web.dto.in;

import com.example.miniecommerce.domain.ItemCarrinho;
import com.example.miniecommerce.domain.Produto;
import jakarta.validation.constraints.Min;

import java.util.List;

public record ItemCarrinhoDto(Long produtoId,
                              @Min(value = 1, message = "A quantidade deve ser maior que zero") int quantidade) { }

