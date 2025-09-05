package com.example.miniecommerce.service.CarrinhoServices;


import com.example.miniecommerce.domain.ItemCarrinho;
import com.example.miniecommerce.infra.exception.EstoqueInsuficiente;
import com.example.miniecommerce.infra.repositories.ItemCarrinhoRepository;
import com.example.miniecommerce.web.dto.in.ItemCarrinhoRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarOuRemoverItem {
    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    private static final Logger log = LoggerFactory.getLogger(AdicionarOuRemoverItem.class);



    public void
    atualizarQuantidadeItem(Long idCarrinho, ItemCarrinhoRequestDto itemCarrinhoDto,  Long usuarioId,long idItem) {


        ItemCarrinho itemCarrinho = itemCarrinhoRepository
                .findByIdAndCarrinhoIdAndCarrinhoUsuarioId(idCarrinho,idItem,usuarioId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado no carrinho do usuário"));

        if (itemCarrinho.getProduto().getEstoque() < itemCarrinhoDto.quantidade()){
            throw new EstoqueInsuficiente("Produto com estoque insuficiente");
        }

        itemCarrinho.setQuantidade(itemCarrinhoDto.quantidade());
        itemCarrinhoRepository.save(itemCarrinho);
        }
    }

