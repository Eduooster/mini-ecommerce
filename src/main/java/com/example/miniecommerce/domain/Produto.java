package com.example.miniecommerce.domain;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.*;
import org.hibernate.annotations.Where;

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
@Where(clause = "deleted = false")

@ToString(exclude = {"itens", "carrinho"})
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoriaProduto;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    @Column(updatable = false)
    private LocalDate dataCadastro = LocalDate.now();
    private Boolean deleted = false;
    private String sku;

    //Um produto pd estar em variso carrinhos
    @OneToMany(mappedBy = "produto")
    private List<ItemCarrinho> itens = new ArrayList<>();
    private String urlImagem;
    private int estoque;


}
