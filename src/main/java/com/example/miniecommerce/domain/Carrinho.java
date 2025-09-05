package com.example.miniecommerce.domain;


import com.example.miniecommerce.web.dto.in.ItemCarrinhoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carrinho_m")
@ToString(exclude = {"usuario", "itens"})

public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @Column(updatable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();
    private LocalDateTime dataAtualizacao;

    private StatusCarrinho status = StatusCarrinho.ABERTO;

    // Um carrinho tem vários itens
    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrinho> itens = new ArrayList<>();

    @ManyToOne
    private Pedido pedido;


    public void atualizaOuRemoveItensCarrinho(List<ItemCarrinhoDto> itensRequest) {

        Map<Long, ItemCarrinhoDto> requestMap = itensRequest.stream()
                .collect(Collectors.toMap(ItemCarrinhoDto::produtoId, i -> i));

        // 1. Atualizar ou remover itens existentes
        this.getItens().removeIf(item -> {
                    if (requestMap.containsKey(item.getProduto().getId())) {
                        item.setQuantidade(requestMap.get(item.getProduto().getId()).quantidade());
                        return false; // mantém no carrinho
                    } else {
                        return true; // remove do carrinho
                    }
                }
        );
    }

    public BigDecimal getTotal() {
        return itens.stream()
                .map(ItemCarrinho::getSubTotal) // preco * quantidade
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
