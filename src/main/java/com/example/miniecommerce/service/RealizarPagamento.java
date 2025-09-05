package com.example.miniecommerce.service;

import com.example.miniecommerce.domain.Pagamento;
import com.example.miniecommerce.domain.Pedido;
import com.example.miniecommerce.domain.StatusPedido;
import com.example.miniecommerce.infra.exception.PedidoNaoEcontrado;
import com.example.miniecommerce.infra.repositories.PagamentoRepository;
import com.example.miniecommerce.infra.repositories.PedidoRepository;
import com.example.miniecommerce.service.StripeServices.StripePaymentService;
import com.example.miniecommerce.web.dto.in.PagamentoCreateDto;
import com.example.miniecommerce.web.dto.in.PaymentDto;
import com.example.miniecommerce.web.dto.out.PaymentResponseDto;
import com.example.miniecommerce.web.dto.out.PaymentStripeResponseDto;
import com.example.miniecommerce.web.mapper.PagamentoMapper;
import com.stripe.exception.StripeException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class RealizarPagamento {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    PagamentoMapper pagamentoMapper;

    @Autowired
    private StripePaymentService stripePaymentService;

    @Transactional
    public PaymentStripeResponseDto realizarPagamento(Long idPedido) throws StripeException {
        Pedido pedido = pedidoRepository.findByIdAndStatus(idPedido, StatusPedido.AGUARDANDO_PAGAMENTO).orElseThrow(()->new PedidoNaoEcontrado("Falha ao encontrar pedido"));


        Long calculoTotalPedido = calcularTotalPedido(pedido);

        PaymentDto pagamento = new PaymentDto(
                pedido.getUsuario().getUsername(),
                pedido.getUsuario().getEmail(),
                pedido.getUsuario().getStripeCustomerId(),calculoTotalPedido,"brl");

        PaymentStripeResponseDto response = stripePaymentService.paymentIntegratedChekout(pagamento);


        Pagamento pagamentoEntity = pedido.getPagamento();
        pagamentoEntity.setStripePaymentIntentId(response.paymentIntentId());
        pagamentoEntity.setStatus(response.status());
        pagamentoEntity.setPaymentMethodId(response.paymentMethod());
        pagamentoEntity.setStripeCustomerId(response.stripeCustomerId());
        pagamentoEntity.setDataPagamento(LocalDateTime.now());
        pagamentoEntity.setMoeda(response.currency());
        pagamentoEntity.setValorTotal(BigDecimal.valueOf(response.valor()));
        //tentar fazer integracao com o webhook do stripe...
        if (response.status().equals("succeeded")) {
            pagamentoEntity.setCreatedAt(Instant.now());
        }
        pagamentoRepository.save(pagamentoEntity);


        return response;

    }

    private Long calcularTotalPedido(Pedido pedido) {

        BigDecimal totalProduto = pedido.getItens().stream().map(item-> item.getProduto().getPreco()).reduce(BigDecimal.ZERO, BigDecimal::add);

        //Inserir a soma do frete
        return totalProduto.longValue();
    }
}
