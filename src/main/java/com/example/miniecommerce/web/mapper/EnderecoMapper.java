package com.example.miniecommerce.web.mapper;

import com.example.miniecommerce.domain.Endereco;
import com.example.miniecommerce.web.dto.in.EnderecoDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {


    Endereco toDomain(EnderecoDto enderecoDto);
}
