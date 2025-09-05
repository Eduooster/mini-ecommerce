package com.example.miniecommerce.web.mapper;


import com.example.miniecommerce.domain.Pagamento;
import com.example.miniecommerce.domain.Pedido;
import com.example.miniecommerce.web.dto.in.PagamentoCreateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {

    Pagamento toEntity(PagamentoCreateDto pagamento);
}
