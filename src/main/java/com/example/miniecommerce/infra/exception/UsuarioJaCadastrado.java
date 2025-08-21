package com.example.miniecommerce.infra.exception;

public class UsuarioJaCadastrado extends RuntimeException {
    public UsuarioJaCadastrado(String message) {
        super(message);
    }
}
