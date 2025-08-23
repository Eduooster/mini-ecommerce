package com.example.miniecommerce.web.dto.out;

import com.example.miniecommerce.domain.ItemCarrinho;
import com.example.miniecommerce.domain.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public record CarrinhoResponseDetailDto(Long id,
                                        String nome,
                                        LocalDateTime DataCriacao, LocalDateTime DataAtualizacao, List<ItemCarrinho> itens) {
}
