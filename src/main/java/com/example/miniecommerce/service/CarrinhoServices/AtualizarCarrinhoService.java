package com.example.miniecommerce.service.CarrinhoServices;

import com.example.miniecommerce.domain.Carrinho;
import com.example.miniecommerce.domain.ItemCarrinho;
import com.example.miniecommerce.domain.Produto;
import com.example.miniecommerce.domain.StatusCarrinho;
import com.example.miniecommerce.infra.exception.CarrinhoDiferenteDoUsuarioLogado;
import com.example.miniecommerce.infra.repositories.CarrinhoRepository;
import com.example.miniecommerce.infra.repositories.ProdutoRepository;
import com.example.miniecommerce.web.dto.in.ItemCarrinhoDto;


import com.example.miniecommerce.web.dto.out.CarrinhoResponseDetailDto;

import com.example.miniecommerce.web.mapper.CarrinhoMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class AtualizarCarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;
    private final CarrinhoMapper carrinhoMapper;
    private final CadastrarCarrinhoService cadastrarCarrinhoService;

    public AtualizarCarrinhoService(CarrinhoRepository carrinhoRepository, ProdutoRepository produtoRepository, CarrinhoMapper carrinhoMapper, CadastrarCarrinhoService cadastrarCarrinhoService) {
        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
        this.carrinhoMapper = carrinhoMapper;
        this.cadastrarCarrinhoService = cadastrarCarrinhoService;
    }

    @Transactional
    public CarrinhoResponseDetailDto atualizarItensCarrinho(Long usuarioId, List<ItemCarrinhoDto> itensRequest, Long idCarrinho){

//        Carrinho carrinho = carrinhoRepository
//                .findByUsuarioIdComItensEProdutos(usuarioId)
//                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        Carrinho carrinho = carrinhoRepository.findByUsuarioIdAndStatus(usuarioId, StatusCarrinho.ABERTO).orElseThrow(()->
               new  EntityNotFoundException("carrinho n enontrado"));

        if (!carrinho.getId().equals(idCarrinho)) {
            throw new CarrinhoDiferenteDoUsuarioLogado("Erro ao sincronizar os dados do carrinho");
        }

        carrinho.atualizaOuRemoveItensCarrinho(itensRequest);

        adicionaItenRequestCarrinho(itensRequest,carrinho);

        carrinho.setDataAtualizacao(LocalDateTime.now());

        carrinhoRepository.save(carrinho);


        CarrinhoResponseDetailDto dto = carrinhoMapper.toCarrinhoResponseDetailDto(carrinho);
        dto.setTotal(carrinho.getTotal());

        return dto;
    }


    private void adicionaItenRequestCarrinho(List<ItemCarrinhoDto> itensRequest,Carrinho carrinho) {
        for (ItemCarrinhoDto itemReq : itensRequest) {
            boolean jaExiste = carrinho.getItens().stream()
                    .anyMatch(i -> i.getProduto().getId().equals(itemReq.produtoId()));
            if (!jaExiste) {
                Produto produto = produtoRepository.findById(itemReq.produtoId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
                novoItem(itemReq,carrinho,produto);
            }
        }

        
    }

    //tentar colocar no mapStruct
    private ItemCarrinho novoItem(ItemCarrinhoDto itemReq,Carrinho carrinho,Produto produto) {
        ItemCarrinho novoItem = new ItemCarrinho();
        novoItem.setProduto(produto);
        novoItem.setQuantidade(itemReq.quantidade());
        novoItem.setCarrinho(carrinho);
        carrinho.getItens().add(novoItem);
        return novoItem;
    }


    }



