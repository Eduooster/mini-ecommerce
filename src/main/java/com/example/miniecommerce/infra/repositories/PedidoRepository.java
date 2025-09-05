package com.example.miniecommerce.infra.repositories;

import com.example.miniecommerce.domain.Pedido;
import com.example.miniecommerce.domain.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido > findByIdAndStatus(Long id, StatusPedido statusPedido);
}
