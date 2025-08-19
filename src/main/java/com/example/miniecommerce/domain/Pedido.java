package com.example.miniecommerce.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido_m")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataCadastro;
    private Boolean deleted;

    @ManyToOne
    private Usuario usuario;

    //1 pedido pode ter n Itend
    @OneToMany
    private List<ItemPedido> itens;

    private String status;
    private BigDecimal total;
    private Endereco enderecoEntrega;

    @OneToOne
    private Pagamento pagamento;
}
