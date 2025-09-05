package com.example.miniecommerce.service.CarrinhoServices;


import com.example.miniecommerce.domain.Carrinho;
import com.example.miniecommerce.domain.StatusCarrinho;
import com.example.miniecommerce.domain.Usuario;
import com.example.miniecommerce.infra.repositories.CarrinhoRepository;
import com.example.miniecommerce.web.dto.out.CarrinhoResponseDetailDto;
import com.example.miniecommerce.web.mapper.CarrinhoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CadastrarCarrinhoService {
    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private CarrinhoMapper carrinhoMapper;


    @Transactional
    public CarrinhoResponseDetailDto cadastrar(Usuario usuario) {
        return carrinhoRepository.findByUsuarioIdAndStatus(usuario.getId(),StatusCarrinho.ABERTO)
                .map(carrinhoMapper::toCarrinhoResponseDetailDto)
                .orElseGet(() -> {
                    Carrinho carrinhoNovo = criarCarrinhoVazioParaUsuario(usuario);
                    carrinhoRepository.save(carrinhoNovo);
                    return carrinhoMapper.toCarrinhoResponseDetailDto(carrinhoNovo);
                });
    }



    Carrinho criarCarrinhoVazioParaUsuario(Usuario usuario){
        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(usuario);
        carrinho.setDataAtualizacao(LocalDateTime.now());
        return carrinho;
    }

}
