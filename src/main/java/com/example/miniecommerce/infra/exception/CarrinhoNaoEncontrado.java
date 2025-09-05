package com.example.miniecommerce.infra.exception;

public class CarrinhoNaoEncontrado extends RuntimeException {
    public CarrinhoNaoEncontrado(String message) {
        super(message);
    }
}
