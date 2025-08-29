package com.example.miniecommerce.service.CarrinhoServices;

import com.example.miniecommerce.domain.Carrinho;
import com.example.miniecommerce.domain.ItemCarrinho;
import com.example.miniecommerce.domain.Produto;
import com.example.miniecommerce.infra.exception.CarrinhoDiferenteDoUsuarioLogado;
import com.example.miniecommerce.infra.repositorie.CarrinhoRepository;
import com.example.miniecommerce.infra.repositorie.ProdutoRepository;
import com.example.miniecommerce.web.dto.in.ItemCarrinhoDto;
import com.example.miniecommerce.web.dto.out.CarrinhoItensResponseDetailDto;

import com.example.miniecommerce.web.dto.out.ItemCarrinhoResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AtualizarCarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;

    public AtualizarCarrinhoService(CarrinhoRepository carrinhoRepository, ProdutoRepository produtoRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public CarrinhoItensResponseDetailDto atualizarItensCarrinho(Long usuarioId, List<ItemCarrinhoDto> itensRequest, Long idCarrinho){

        Carrinho carrinho = carrinhoRepository
                .findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        if (!carrinho.getId().equals(idCarrinho)) {
            throw new CarrinhoDiferenteDoUsuarioLogado("Erro ao sincronizar os dados do carrinho");
        }

        atualizaOuRemoveItensCarrinho(itensRequest,carrinho);

        adicionaItenRequestCarrinho(itensRequest,carrinho);

        carrinho.setDataAtualizacao(LocalDateTime.now());

        carrinhoRepository.save(carrinho);

        toCarrinhoItensResponseDetailDto(carrinho);

        return toCarrinhoItensResponseDetailDto(carrinho);
    }

    private CarrinhoItensResponseDetailDto toCarrinhoItensResponseDetailDto(Carrinho carrinho) {
        return new CarrinhoItensResponseDetailDto(
                carrinho.getId(),
                carrinho.getDataCadastro(),
                carrinho.getDataAtualizacao(),
                converterItensCarrinhoToItemCarrinhoResponseDto(carrinho)
        );
    }

    private List<ItemCarrinhoResponseDto> converterItensCarrinhoToItemCarrinhoResponseDto(Carrinho carrinho) {
        return carrinho.getItens().stream()
                .map(i -> new ItemCarrinhoResponseDto(
                        i.getProduto().getId(),
                        i.getProduto().getNome(),
                        i.getQuantidade(),
                        i.getProduto().getPreco(),
                        i.getProduto().getPreco().multiply(BigDecimal.valueOf(i.getQuantidade()))
                ))
                .toList();
    }

    private void adicionaItenRequestCarrinho(List<ItemCarrinhoDto> itensRequest,Carrinho carrinho) {
        for (ItemCarrinhoDto itemReq : itensRequest) {
            boolean jaExiste = carrinho.getItens().stream()
                    .anyMatch(i -> i.getProduto().getId().equals(itemReq.produtoId()));
            if (!jaExiste) {
                Produto produto = produtoRepository.findById(itemReq.produtoId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
                ItemCarrinho novoItem = new ItemCarrinho();
                novoItem.setProduto(produto);
                novoItem.setQuantidade(itemReq.quantidade());
                novoItem.setCarrinho(carrinho);
                carrinho.getItens().add(novoItem);
            }
        }

        
    }

    private void atualizaOuRemoveItensCarrinho(List<ItemCarrinhoDto> itensRequest, Carrinho carrinho) {
        Map<Long, ItemCarrinhoDto> requestMap = itensRequest.stream()
                .collect(Collectors.toMap(ItemCarrinhoDto::produtoId, i -> i));

        // 1. Atualizar ou remover itens existentes
        carrinho.getItens().removeIf(item -> {
            if (requestMap.containsKey(item.getProduto().getId())) {
                item.setQuantidade(requestMap.get(item.getProduto().getId()).quantidade());
                return false; // mantém no carrinho
            } else {
                return true; // remove do carrinho
            }
        }
        );

    }


}
