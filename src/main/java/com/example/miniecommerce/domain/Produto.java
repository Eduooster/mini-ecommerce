package com.example.miniecommerce.domain;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto_m")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoriaProduto;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private LocalDate dataCadastro;
    private Boolean deleted;

    //Um produto pd estar em variso carrinhos
    @OneToMany(mappedBy = "produto")
    private List<ItemCarrinho> itens = new ArrayList<>();


}
