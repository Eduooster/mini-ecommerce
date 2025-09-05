package com.example.miniecommerce.service.PedidoServices;

import com.example.miniecommerce.domain.Pedido;
import com.example.miniecommerce.domain.StatusPedido;
import com.example.miniecommerce.infra.exception.PedidoNaoEcontrado;
import com.example.miniecommerce.infra.repositories.PedidoRepository;
import com.example.miniecommerce.service.MellhorEnvioServices.MelhorEnvioApiService;
import com.example.miniecommerce.web.dto.in.PedidoEnviarRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoEnviarService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private MelhorEnvioApiService melhorEnvioApiService;

    public void enviarPedido(Long id, PedidoEnviarRequestDto dto) {

        Pedido pedido  = pedidoRepository.findByIdAndStatus(id, StatusPedido.FINALIZADO).orElseThrow(()-> new PedidoNaoEcontrado("Erro ao enviar pedido"));

        melhorEnvioApiService.criarEnvio(pedido,dto);






    }
}
