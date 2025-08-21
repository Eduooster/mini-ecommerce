package com.example.miniecommerce.web.dto.Usuario.in;

import com.example.miniecommerce.domain.Endereco;
import com.example.miniecommerce.web.dto.Endereco.EnderecoDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioCreateRequestDto(@NotBlank String username,
                                      @NotBlank String password,
                                      @NotBlank @Email String email,
                                      @NotBlank EnderecoDto endereco) {
}
