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
import com.example.miniecommerce.web.dto.out.ProdutoPedidoResponseDto;
import com.example.miniecommerce.web.mapper.PagamentoMapper;
import com.stripe.exception.StripeException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    PagamentoMapper pagamentoMapper;



    @Autowired
    private StripePaymentService stripePaymentService;

    @Transactional
    public void atualizarPagamento(Long idPedido, PagamentoCreateDto pagamentodto) {

        Pedido pedido = pedidoRepository.findByIdAndStatus(idPedido, StatusPedido.AGUARDANDO_PAGAMENTO).orElseThrow(()->new PedidoNaoEcontrado("Falha ao encontrar pedido"));


        Pagamento pagamentoEntity = pagamentoMapper.toEntity(pagamentodto);
        pedido.setPagamento(pagamentoEntity);

        pedidoRepository.save(pedido);
        pagamentoRepository.save(pagamentoEntity);

    }



}
