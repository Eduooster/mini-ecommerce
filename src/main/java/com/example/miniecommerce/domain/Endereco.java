package com.example.miniecommerce.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;

    // getters e setters
}

