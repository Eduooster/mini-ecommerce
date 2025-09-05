package com.example.miniecommerce.infra.repositories;

import com.example.miniecommerce.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
