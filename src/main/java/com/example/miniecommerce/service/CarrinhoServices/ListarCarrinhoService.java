package com.example.miniecommerce.service.CarrinhoServices;

import com.example.miniecommerce.domain.Carrinho;
import com.example.miniecommerce.infra.repositories.CarrinhoRepository;
import com.example.miniecommerce.web.dto.out.CarrinhoResponseDetailDto;
import com.example.miniecommerce.web.mapper.CarrinhoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarCarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private CarrinhoMapper carrinhoMapper;

    public Page<CarrinhoResponseDetailDto> listarCarrinhos(Pageable paginacao) {
        return carrinhoRepository.findAll(paginacao).map(carrinhoMapper::toCarrinhoResponseDetailDto);
    }

    public CarrinhoResponseDetailDto mostrarCarrinho(Long idCarrinho) {

        Carrinho carrinho = carrinhoRepository.findById(idCarrinho).orElseThrow(()->new EntityNotFoundException("carrinho n envotnra"));
        return carrinhoMapper.toCarrinhoResponseDetailDto(carrinho);
    }
}

