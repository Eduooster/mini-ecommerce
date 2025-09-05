package com.example.miniecommerce.service.PedidoServices;

import com.example.miniecommerce.domain.Pedido;
import com.example.miniecommerce.domain.StatusPedido;
import com.example.miniecommerce.infra.exception.PedidoNaoEcontrado;
import com.example.miniecommerce.infra.repositories.PedidoRepository;
import com.example.miniecommerce.service.EntregaService;
import com.example.miniecommerce.web.dto.in.FreteDto;
import com.example.miniecommerce.web.dto.out.PedidoStatusFrete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoFreteService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    EntregaService entregaService;



    public PedidoStatusFrete atualizarFrete(Long idPedido, FreteDto freteDto) {
        Pedido pedido = pedidoRepository.findByIdAndStatus(idPedido,StatusPedido.AGUARDANDO_FRETE).orElseThrow(()->new PedidoNaoEcontrado("pedido nao encontrado"));

        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        pedido.setTipoEntrega(freteDto.tipoEntrega());
        pedidoRepository.save(pedido);

        return new PedidoStatusFrete(freteDto,pedido.getStatus());
    }
}
