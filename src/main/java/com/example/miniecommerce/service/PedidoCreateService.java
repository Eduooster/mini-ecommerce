package com.example.miniecommerce.service;

import com.example.miniecommerce.domain.Pedido;
import com.example.miniecommerce.infra.repositorie.PedidoRepository;
import com.example.miniecommerce.web.dto.in.PedidoCreateRequestDto;
import com.example.miniecommerce.web.dto.out.PedidoDetailsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoCreateService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoDetailsResponseDto salvar(PedidoCreateRequestDto dto){


    }


}
