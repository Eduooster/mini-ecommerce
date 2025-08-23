package com.example.miniecommerce.infra.repositorie;

import com.example.miniecommerce.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findBySku(String sku);

    boolean existsBySku(String sku);
}
