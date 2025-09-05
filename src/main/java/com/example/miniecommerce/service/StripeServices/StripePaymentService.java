package com.example.miniecommerce.service.StripeServices;


import com.example.miniecommerce.domain.Usuario;
import com.example.miniecommerce.infra.repositories.PagamentoRepository;
import com.example.miniecommerce.infra.repositories.UsuarioRepository;
import com.example.miniecommerce.web.dto.in.PaymentDto;
import com.example.miniecommerce.web.dto.out.PaymentResponseDto;
import com.example.miniecommerce.web.dto.out.PaymentStripeResponseDto;
import com.example.miniecommerce.web.dto.out.ProdutoPedidoResponseDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class StripePaymentService {



    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CustomerService customerService;

    public PaymentStripeResponseDto paymentIntegratedChekout(PaymentDto dto) throws StripeException {

        Stripe.apiKey = System.getenv("STRIPE_API_KEY");

        Usuario usuario = usuarioRepository.findByStripeCustomerId(dto.getStripeCustomerId());

        String paymentIntentCreate = null;

        if (usuario != null) {
            Customer customer = customerService.customerSearch(dto.getCustomerEmail(), dto.getCustomerName());

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(dto.getAmount())
                    .setCurrency("br")
                    .setCustomer(customer.getId())
                    .setAutomaticPaymentMethods(
                            PaymentIntentCreateParams.AutomaticPaymentMethods
                                    .builder()
                                    .setEnabled(true)
                                    .build()
                    ).build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);



            return new PaymentStripeResponseDto(
                    paymentIntent.getId(),
                    paymentIntent.getClientSecret(),
                    paymentIntent.getStatus() != null ? paymentIntent.getStatus().toString() : "",
                    paymentIntent.getAmount(),
                    paymentIntent.getCurrency(),
                    paymentIntent.getPaymentMethod(),
                    customer.getId()

            );

        }
        return null;
    }


}
