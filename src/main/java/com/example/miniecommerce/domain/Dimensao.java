package com.example.miniecommerce.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Dimensao {
        private double peso;       // kg
        private double length; // cm
        private double width;     // cm
        private double height;      // cm
        private double valor;
        private double weight;
        private double insurance_value;

}
