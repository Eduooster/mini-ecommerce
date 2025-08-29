package com.example.miniecommerce.infra.exception;

public class CarrinhoDiferenteDoUsuarioLogado extends RuntimeException {
    public CarrinhoDiferenteDoUsuarioLogado(String message) {
        super(message);
    }
}
