package com.example.miniecommerce.infra.exception;

public class PedidoNaoEcontrado extends RuntimeException {
    public PedidoNaoEcontrado(String message) {
        super(message);
    }
}
