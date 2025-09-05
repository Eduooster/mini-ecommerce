package com.example.miniecommerce.service.PedidoServices;

import com.example.miniecommerce.domain.Pedido;
import com.example.miniecommerce.domain.StatusPedido;
import com.example.miniecommerce.infra.exception.PedidoNaoEcontrado;
import com.example.miniecommerce.infra.repositories.PedidoRepository;
import com.example.miniecommerce.web.dto.in.EnderecoDto;
import com.example.miniecommerce.web.dto.out.PedidoStatusEnderecoDto;
import com.example.miniecommerce.web.mapper.EnderecoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoEnderecoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    EnderecoMapper enderecoMapper;




    public PedidoStatusEnderecoDto atualizar(Long idPedido, EnderecoDto enderecoDto) {

        Pedido pedido = pedidoRepository.findByIdAndStatus(idPedido,StatusPedido.AGUARDANDO_ENDERECO).orElseThrow(()->new PedidoNaoEcontrado("Pedido nao encontrado"));

        pedido.setEnderecoEntrega(enderecoMapper.toDomain(enderecoDto));
        pedido.setStatus(StatusPedido.AGUARDANDO_FRETE);
        pedidoRepository.save(pedido);

        return new PedidoStatusEnderecoDto(enderecoDto,pedido.getStatus());


    }
}
