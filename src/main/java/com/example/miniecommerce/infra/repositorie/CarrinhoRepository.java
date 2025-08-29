package com.example.miniecommerce.infra.repositorie;

import com.example.miniecommerce.domain.Carrinho;
import com.example.miniecommerce.domain.ItemCarrinho;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    Optional<Carrinho> findByUsuarioId(Long id);

    Optional<Carrinho> findByUsuarioIdAndStatus(Long id, String ativo);

   Optional<ItemCarrinho>findByUsuarioIdAndItens_Id(Long usuarioId, Long id);

    Carrinho findByIdAndStatus(Long idCarrinho, String ativo);
}
