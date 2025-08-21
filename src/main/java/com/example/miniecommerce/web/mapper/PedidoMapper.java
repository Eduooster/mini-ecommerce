package com.example.miniecommerce.web.mapper;

import com.example.miniecommerce.domain.Pedido;
import com.example.miniecommerce.web.dto.Pedido.out.PedidoReponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    PedidoReponseDto toPedidoReponseDto(Pedido pedido);
}
