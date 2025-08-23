package com.example.miniecommerce.web.controller;


import com.example.miniecommerce.service.AutenticacaoService;
import com.example.miniecommerce.web.dto.in.DadoAuthRequestDto;
import com.example.miniecommerce.web.dto.out.DadosTokenJwt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
    public ResponseEntity efetuarAutenticacao(@RequestBody @Valid DadoAuthRequestDto dados) {
        String tokenJWT = autenticacaoService.autenticar(dados);



        return ResponseEntity.ok(new DadosTokenJwt(tokenJWT));
    }
}
