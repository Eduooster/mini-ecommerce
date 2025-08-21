package com.example.miniecommerce.infra.repositorie;

import com.example.miniecommerce.domain.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
}
