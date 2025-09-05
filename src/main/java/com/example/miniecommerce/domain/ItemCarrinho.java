package com.example.miniecommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ItemCarrinho_m")
@ToString(exclude = {"carrinho","produto"})
public class ItemCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pedido pedido;

    // Muitos itens podem estar associados a um produto
    @ManyToOne
    private Carrinho carrinho;

    // Muitos itens podem estar associados a um carrinho
    @ManyToOne
    private Produto produto;
    private int quantidade;

    public BigDecimal getSubTotal() {
        return produto.getPreco().multiply(new BigDecimal(quantidade));
    }
}
