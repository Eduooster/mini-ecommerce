package com.example.miniecommerce.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity

@Data
@Table(name = "pedido_m")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false) // nunca será alterada depois do insert
    private LocalDateTime dataCadastro = LocalDateTime.now();
    private Boolean deleted = false;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ItemPedido> itens;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private BigDecimal total;


    private TipoEntrega tipoEntrega;


    private Endereco enderecoEntrega;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Pagamento pagamento;

    @OneToOne
    private Carrinho carrinho;


}
