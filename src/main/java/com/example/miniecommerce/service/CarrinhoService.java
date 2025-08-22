package com.example.miniecommerce.service;


import com.example.miniecommerce.domain.Carrinho;
import com.example.miniecommerce.domain.Usuario;
import com.example.miniecommerce.infra.repositorie.CarrinhoRepository;
import com.example.miniecommerce.web.dto.Carrinho.in.CarrinhoCreateRequestDto;
import com.example.miniecommerce.web.dto.Carrinho.out.CarrinhoResponseDetailDto;
import com.example.miniecommerce.web.mapper.CarrinhoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarrinhoService {
    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private CarrinhoMapper carrinhoMapper;

    public CarrinhoResponseDetailDto cadastrar(@Valid  Usuario usuario) {

        Optional<Carrinho> carrinho = carrinhoRepository.findByUsuarioIdAndStatus(usuario.getId(), "ATIVO");


        return carrinhoRepository.findByUsuarioId(usuario.getId())
                .map(carrinhoMapper::toCarrinhoResponseDetailDto) // se existir, retorna o mapeado
                .orElseGet(() -> { // senão, cria um novo
                    Carrinho carrinhoNovo = criarCarrinhoVazioParaUsuario(usuario);
                    carrinhoRepository.save(carrinhoNovo);
                    return carrinhoMapper.toCarrinhoResponseDetailDto(carrinhoNovo);
                });

    }

    Carrinho criarCarrinhoVazioParaUsuario(Usuario usuario){
        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(usuario);
        return carrinho;
    }
}
