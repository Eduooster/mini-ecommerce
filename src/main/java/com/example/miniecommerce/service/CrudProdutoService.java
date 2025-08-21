package com.example.miniecommerce.service;

import com.example.miniecommerce.domain.Produto;
import com.example.miniecommerce.infra.exception.ProdutoJaCadastrado;
import com.example.miniecommerce.infra.repositorie.ProdutoRepository;
import com.example.miniecommerce.web.dto.Produto.in.ProdutoCreateRequestDto;
import com.example.miniecommerce.web.dto.Produto.out.ProdutoDetailsResponseDto;
import com.example.miniecommerce.web.mapper.ProdutoMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CrudProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public CrudProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }


    public ProdutoDetailsResponseDto cadastrar(@Valid ProdutoCreateRequestDto dto) {

        Produto produto = produtoMapper.toProduto(dto);

        if (produtoRepository.existsBySku(produto.getSku())) {
            throw new ProdutoJaCadastrado("Produto com sku já cadastrado!");
        }


        produtoRepository.save(produto);


        return produtoMapper.toResponseProdutoDetailDto(produto);
    }

    public Page<ProdutoDetailsResponseDto> listar(Pageable paginacao) {
        return produtoRepository
                .findAll(paginacao)
                .map(produtoMapper::toResponseProdutoDetailDto);

    }

    public ProdutoDetailsResponseDto deletar(Long id) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("id usuario inexistente"));

        produto.setDeleted(true);

        return produtoMapper.toResponseProdutoDetailDto(produto);

    }

    public ProdutoDetailsResponseDto detalhar(Long id) {

        Produto produto = produtoRepository
                .findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Produto não encontrado!"));

        return produtoMapper.toResponseProdutoDetailDto(produto);
    }
}
