package com.example.miniecommerce.service.AutenticacaoServices;

import com.example.miniecommerce.infra.security.TokenService;
import com.example.miniecommerce.web.dto.in.DadoAuthRequestDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String autenticar(@Valid DadoAuthRequestDto dados) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.password());

        var tokenJwt =tokenService.gerarToken((UserDetails) authenticationManager.authenticate(authenticationToken).getPrincipal());

        return tokenJwt;
    }
}
