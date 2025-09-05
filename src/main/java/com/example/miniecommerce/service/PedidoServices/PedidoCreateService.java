package com.example.miniecommerce.service.PedidoServices;

import com.example.miniecommerce.domain.*;
import com.example.miniecommerce.infra.exception.CarrinhoNaoEncontrado;
import com.example.miniecommerce.infra.repositories.CarrinhoRepository;
import com.example.miniecommerce.infra.repositories.PedidoRepository;

import com.example.miniecommerce.web.dto.out.PedidoDetailsResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoCreateService {

    @Autowired
    PedidoRepository pedidoRepository;


    @Autowired
    CarrinhoRepository carrinhoRepository;



    @Transactional
    public PedidoDetailsResponseDto salvar(Usuario usuario) {


        Carrinho carrinho = carrinhoRepository.findByUsuarioIdAndStatus(usuario.getId(),StatusCarrinho.ABERTO).orElseThrow(()->new CarrinhoNaoEncontrado("Carrinho n encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCarrinho(carrinho);
        pedido.setUsuario(usuario);
        pedido.setStatus(StatusPedido.AGUARDANDO_ENDERECO);

        List<ItemPedido> itensPedido = carrinho.getItens().stream()
                .map(itemCarrinho -> {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setProduto(itemCarrinho.getProduto());
                    itemPedido.setQuantidade(itemCarrinho.getQuantidade());
                    itemPedido.setPrecoUnit(itemCarrinho.getProduto().getPreco());
                    itemPedido.setPedido(pedido);
                    return itemPedido;
                })
                .toList();
        pedido.setItens(itensPedido);

        carrinho.setStatus(StatusCarrinho.FECHADO);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        criarCarrinhoVazioUsuario(usuario);

        return new PedidoDetailsResponseDto(pedidoSalvo.getId());

    }

    private void criarCarrinhoVazioUsuario(Usuario usuario) {
        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(usuario);
        carrinho.setDataAtualizacao(LocalDateTime.now());
        carrinho.setItens(new ArrayList<>());
        carrinho.setDataCadastro(LocalDateTime.now());
        carrinhoRepository.save(carrinho);

    }


}
