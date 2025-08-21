package com.example.miniecommerce.web.dto.Usuario.out;

import com.example.miniecommerce.domain.Role;
import com.example.miniecommerce.web.dto.Endereco.EnderecoDto;
import com.example.miniecommerce.web.dto.Notificacao.out.NotificacaoResponseDto;
import com.example.miniecommerce.web.dto.Pedido.out.PedidoReponseDto;
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
