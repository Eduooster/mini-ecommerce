package com.example.miniecommerce.service.CarrinhoServices;

import com.example.miniecommerce.domain.Carrinho;
import com.example.miniecommerce.infra.exception.CarrinhoNaoEncontrado;
import com.example.miniecommerce.infra.repositories.CarrinhoRepository;
import com.example.miniecommerce.web.dto.out.CarrinhoResponseDetailDto;
import com.example.miniecommerce.web.mapper.CarrinhoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LimparCarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private CarrinhoMapper carrinhoMapper;

    public CarrinhoResponseDetailDto limparCarrinho(Long id, Long idUsuario) {

        Carrinho carrinho = carrinhoRepository.findByUsuarioId(idUsuario).orElseThrow(
                ()->new CarrinhoNaoEncontrado("Carrinho nao encontrado!")
        );

        //Clear + orphanremoval = true: deleta as relacoes q outra entidade tinha...Permitindo
        //atualizar os dados sem ter conflito de relacao
        carrinho.getItens().clear();


        carrinhoRepository.save(carrinho);

        return carrinhoMapper.toCarrinhoResponseDetailDto(carrinho);




    }
}
