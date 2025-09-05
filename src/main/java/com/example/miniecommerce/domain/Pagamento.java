package com.example.miniecommerce.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento_m")
@Data
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant createdAt;//dto
    private Instant confirmedAt;//stripe
    private String paymentMethodId;//stripe
    private String metodoPagamento;//dto
    private BigDecimal valorTotal;//dto
    private LocalDateTime dataPagamento;//dto
    private String moeda = "brl";
    @OneToOne
    private Pedido pedido;//service
    private String stripePaymentIntentId;//stripe
    private String stripeCustomerId;//stripe
    private String status;//stripe
}
