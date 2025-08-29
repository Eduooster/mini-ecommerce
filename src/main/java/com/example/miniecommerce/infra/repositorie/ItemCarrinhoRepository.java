package com.example.miniecommerce.infra.repositorie;

import com.example.miniecommerce.domain.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {
    List<ItemCarrinho> findByCarrinhoId(Long idCarrinho);

    Optional<ItemCarrinho> findByCarrinhoIdAndIdAndCarrinhoUsuarioId(Long idCarrinho, Long idItem, Long usuarioId);
}
