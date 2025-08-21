package com.example.miniecommerce.infra.exception;

import com.example.miniecommerce.domain.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorErros {

    @ExceptionHandler(UsuarioJaCadastrado.class)
    public ResponseEntity<Map<String, String>> UsuarioJaCadastrado(UsuarioJaCadastrado ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(detalhar(ex));
    }

    @ExceptionHandler(UsuarioNaoEncontrado.class)
    public ResponseEntity<Map<String, String>> UsuarioNotFound(UsuarioNaoEncontrado ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhar(ex));
    }

    @ExceptionHandler(ProdutoJaCadastrado.class)
    public ResponseEntity<Map<String,String>> ProdutoJaCadastrado(ProdutoJaCadastrado ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(detalhar(ex));
    }

    public Map<String,String> detalhar(Exception ex) {
        Map<String,String> map = new HashMap<>();
        map.put("erro", ex.getClass().getSimpleName());
        map.put("mensagem", ex.getMessage());
        return map;
    }

}
