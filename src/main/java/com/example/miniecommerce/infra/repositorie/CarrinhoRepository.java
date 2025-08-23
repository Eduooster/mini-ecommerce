package com.example.miniecommerce.infra.repositorie;

import com.example.miniecommerce.domain.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    Optional<Carrinho> findByUsuarioId(Long id);

    Optional<Carrinho> findByUsuarioIdAndStatus(Long id, String ativo);
}
