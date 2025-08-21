package com.example.miniecommerce.infra.exception;

public class ProdutoJaCadastrado extends RuntimeException {
    public ProdutoJaCadastrado(String message) {
        super(message);
    }
}
