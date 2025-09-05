package com.example.miniecommerce.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class EntregaService {

    public enum StatusEntrega {
        PENDENTE,
        EM_TRANSITO,
        ENTREGUE
    }

    public enum LocalOrigem {
        ARMAZEM1,
        ARMAZEM2
    }

    public static class Frete {
        private BigDecimal valor;
        private int prazoDias;
        private String codigoRastreamento;
        private StatusEntrega status;

        public Frete(BigDecimal valor, int prazoDias, String codigoRastreamento, StatusEntrega status) {
            this.valor = valor;
            this.prazoDias = prazoDias;
            this.codigoRastreamento = codigoRastreamento;
            this.status = status;
        }

        public BigDecimal getValor() { return valor; }
        public int getPrazoDias() { return prazoDias; }
        public String getCodigoRastreamento() { return codigoRastreamento; }
        public StatusEntrega getStatus() { return status; }

        public void setStatus(StatusEntrega status) { this.status = status; }
    }

    private static final BigDecimal VALOR_BASE = BigDecimal.valueOf(10.0);

    // Método principal para calcular frete
    public Frete calcularFrete(String cepDestino, LocalOrigem origem) {
        validarCep(cepDestino);

        // Exemplo de regra: último dígito do CEP influencia preço
        char ultimoDigito = cepDestino.charAt(cepDestino.length() - 1);
        int multiplicador = Character.getNumericValue(ultimoDigito) + 1;

        BigDecimal valorFinal = VALOR_BASE.multiply(BigDecimal.valueOf(multiplicador));

        // Prazo de entrega baseado na origem
        int prazoDias = origem == LocalOrigem.ARMAZEM1 ? 5 : 7;

        // Código de rastreio gerado aleatoriamente
        String codigoRastreamento = gerarRastreamento();

        return new Frete(valorFinal, prazoDias, codigoRastreamento, StatusEntrega.PENDENTE);
    }

    // Validação simples de CEP (simulação)
    private void validarCep(String cep) {
        if (cep == null || !cep.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP inválido: " + cep);
        }
    }

    // Gera um código de rastreio aleatório
    private String gerarRastreamento() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder("R");
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    // Atualiza status da entrega
    public void atualizarStatus(Frete frete, StatusEntrega novoStatus) {
        frete.setStatus(novoStatus);
    }

}
