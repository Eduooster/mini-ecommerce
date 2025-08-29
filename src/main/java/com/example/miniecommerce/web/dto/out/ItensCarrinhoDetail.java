package com.example.miniecommerce.web.dto.out;

import com.example.miniecommerce.domain.ItemCarrinho;
import com.example.miniecommerce.web.dto.in.ItemCarrinhoDto;

import java.util.List;

public record ItensCarrinhoDetail(List<ItemListDto> itens) {
}
