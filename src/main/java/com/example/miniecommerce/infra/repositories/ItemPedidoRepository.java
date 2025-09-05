package com.example.miniecommerce.infra.repositories;

import com.example.miniecommerce.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
