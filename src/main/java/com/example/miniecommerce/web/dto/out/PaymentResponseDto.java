package com.example.miniecommerce.web.dto.out;

import com.example.miniecommerce.web.dto.in.EnderecoDto;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PaymentResponseDto(
        String paymentIntentId,
        String clientSecret,
        String paymentMethodId,
        String status,
        Long amount,
        String currency,
        String customerId,
        String customerEmail,
        LocalDateTime createdAt

) {
}
