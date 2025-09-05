package com.example.miniecommerce.web.dto.out;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CarrinhoResponseDetailDto {
    private  Long id;
    private List<ItemCarrinhoResponseDto> itens;
    private BigDecimal total;
}
