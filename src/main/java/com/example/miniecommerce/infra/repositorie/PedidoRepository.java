package com.example.miniecommerce.infra.repositorie;

import com.example.miniecommerce.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
