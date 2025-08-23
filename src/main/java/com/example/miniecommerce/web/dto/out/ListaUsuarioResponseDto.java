package com.example.miniecommerce.web.dto.out;

import com.example.miniecommerce.domain.Role;

import java.time.LocalDateTime;
import java.util.Set;

public record ListaUsuarioResponseDto(
        String username,
        String email,
        LocalDateTime dataCadastro,
        EnderecoDto endereco,
        Set<Role> roles
) {
}
