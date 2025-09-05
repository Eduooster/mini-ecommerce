package com.example.miniecommerce.infra.repositories;

import com.example.miniecommerce.domain.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);



    Page<Usuario> findAllByDeletedFalse(Pageable pageable);

    Usuario findByStripeCustomerId(String stripeCustomerId);
}
