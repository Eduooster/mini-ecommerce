package com.example.miniecommerce.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "itemPedido_m")
@Data
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;

    //
    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Produto produto;
    private BigDecimal precoUnit;
    private BigDecimal subtotal;

}
