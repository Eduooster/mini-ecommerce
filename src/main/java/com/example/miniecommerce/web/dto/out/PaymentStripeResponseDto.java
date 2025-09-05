package com.example.miniecommerce.web.dto.out;

import java.math.BigDecimal;

public record PaymentStripeResponseDto(String paymentIntentId,
                                       String clientSecret,
                                       String status,
                                       Long valor,
                                       String currency,
                                       String paymentMethod,
                                       String stripeCustomerId
                                      ) {
}
