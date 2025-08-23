package com.example.miniecommerce.web.dto.out;

import com.example.miniecommerce.domain.Role;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record UsuarioDetailsResponseDto(@NotBlank Long id,
                                        @NotBlank String username,
                                        @NotBlank String email,
                                        @NotBlank LocalDateTime dataCadastro,
                                        @NotBlank EnderecoDto endereco,
                                        @NotBlank Set<Role> roles,
                                        @NotBlank List<PedidoReponseDto> pedidos,
                                        @NotBlank List<NotificacaoResponseDto> notificacoes) {
}
