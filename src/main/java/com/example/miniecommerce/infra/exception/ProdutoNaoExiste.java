package com.example.miniecommerce.infra.exception;

public class ProdutoNaoExiste extends RuntimeException {
    public ProdutoNaoExiste(String message) {
        super(message);
    }
}
