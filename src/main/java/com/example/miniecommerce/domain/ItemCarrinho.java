package com.example.miniecommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ItemCarrinho_m")
public class ItemCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Muitos itens podem estar associados a um produto
    @ManyToOne
    private Carrinho carrinho;

    // Muitos itens podem estar associados a um carrinho
    @ManyToOne
    private Produto produto;
    private int quantidade;
}
