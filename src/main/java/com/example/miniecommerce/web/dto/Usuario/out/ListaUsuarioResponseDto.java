package com.example.miniecommerce.web.dto.Usuario.out;

import com.example.miniecommerce.domain.Role;
import com.example.miniecommerce.web.dto.Endereco.EnderecoDto;
import com.example.miniecommerce.web.dto.Notificacao.out.NotificacaoResponseDto;
import com.example.miniecommerce.web.dto.Pedido.out.PedidoReponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record ListaUsuarioResponseDto(
        String username,
        String email,
        LocalDateTime dataCadastro,
        EnderecoDto endereco,
        Set<Role> roles
) {
}
