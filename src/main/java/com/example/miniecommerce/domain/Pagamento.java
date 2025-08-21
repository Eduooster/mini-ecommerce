package com.example.miniecommerce.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento_m")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String metodo;
    private BigDecimal valor;
    private LocalDateTime dataPagamento;
    private String moeda;
    @OneToOne
    private Pedido pedido;
    private String stripe_payment_id;
    private String stripe_charge_id;
    private String recibo_url;

}
