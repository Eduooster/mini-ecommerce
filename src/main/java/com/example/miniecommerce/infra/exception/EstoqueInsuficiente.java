package com.example.miniecommerce.infra.exception;

public class EstoqueInsuficiente extends RuntimeException {
    public EstoqueInsuficiente(String message) {
        super(message);
    }
}
