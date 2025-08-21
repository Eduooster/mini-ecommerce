package com.example.miniecommerce.web.mapper;

import com.example.miniecommerce.domain.Usuario;
import com.example.miniecommerce.web.dto.Usuario.in.UsuarioCreateRequestDto;
import com.example.miniecommerce.web.dto.Usuario.out.ListaUsuarioResponseDto;
import com.example.miniecommerce.web.dto.Usuario.out.UsuarioDetailsResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {PedidoMapper.class})
public interface UsuarioMapper {

    Usuario toUsuario(UsuarioCreateRequestDto dto);

    UsuarioDetailsResponseDto toUsuarioDetailsReponseDto(Usuario usuario);

    ListaUsuarioResponseDto toListaResponseDto(Usuario usuario);
}
