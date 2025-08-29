package com.example.miniecommerce.service.CarrinhoServices;


import com.example.miniecommerce.domain.ItemCarrinho;
import com.example.miniecommerce.infra.repositorie.ItemCarrinhoRepository;
import com.example.miniecommerce.web.dto.in.ItemCarrinhoRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarOuRemoverItem {
    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;



    public void atualizarQuantidadeItem(Long idCarrinho,ItemCarrinhoRequestDto itemReq, Long usuarioId) {

        ItemCarrinho itemCarrinho = itemCarrinhoRepository
                .findByCarrinhoIdAndIdAndCarrinhoUsuarioId(idCarrinho, itemReq.itemId(), usuarioId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado no carrinho do usuário"));

        itemCarrinho.setQuantidade(itemReq.quantidade());
        itemCarrinhoRepository.save(itemCarrinho);
        }
    }

