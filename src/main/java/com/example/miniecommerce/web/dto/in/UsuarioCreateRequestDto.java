package com.example.miniecommerce.web.dto.in;

import com.example.miniecommerce.web.dto.out.EnderecoDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioCreateRequestDto(@NotBlank String username,
                                      @NotBlank String password,
                                      @NotBlank @Email String email,
                                      @NotBlank EnderecoDto endereco) {
}
