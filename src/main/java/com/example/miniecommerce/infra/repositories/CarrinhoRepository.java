package com.example.miniecommerce.infra.repositories;

import com.example.miniecommerce.domain.Carrinho;

import com.example.miniecommerce.domain.StatusCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    Optional<Carrinho> findByUsuarioId(Long id);

    @Query("""
    select c 
    from Carrinho c 
    join fetch c.itens i 
    join fetch i.produto 
    where c.usuario.id = :usuarioId
""")
    Optional<Carrinho> findByUsuarioIdComItensEProdutos(@Param("usuarioId") Long usuarioId);


    Optional<Carrinho> findByUsuarioIdAndStatus(Long id, StatusCarrinho statusCarrinho);
}
