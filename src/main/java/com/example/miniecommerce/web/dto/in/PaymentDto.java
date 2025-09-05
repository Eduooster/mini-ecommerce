package com.example.miniecommerce.web.dto.in;


import com.example.miniecommerce.domain.Produto;
import com.example.miniecommerce.web.dto.out.ProdutoDetailsResponseDto;
import com.example.miniecommerce.web.dto.out.ProdutoPedidoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentDto {
    String customerName;
    String customerEmail;
    String stripeCustomerId;
    Long amount;
    String currency;


}
