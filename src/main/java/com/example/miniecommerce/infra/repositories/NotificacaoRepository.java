package com.example.miniecommerce.infra.repositories;

import com.example.miniecommerce.domain.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
}
