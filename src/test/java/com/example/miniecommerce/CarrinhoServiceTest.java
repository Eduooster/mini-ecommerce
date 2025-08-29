package com.example.miniecommerce;


import com.example.miniecommerce.domain.Carrinho;
import com.example.miniecommerce.domain.Produto;
import com.example.miniecommerce.domain.Usuario;
import com.example.miniecommerce.infra.repositorie.CarrinhoRepository;
import com.example.miniecommerce.infra.repositorie.ProdutoRepository;
import com.example.miniecommerce.infra.repositorie.UsuarioRepository;
import com.example.miniecommerce.service.CarrinhoServices.CadastrarCarrinhoService;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarrinhoServiceTest{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastrarCarrinhoService carrinhoService;

    private Usuario usuarioTeste;
    private Carrinho carrinhoTeste;
    private Produto produtoTeste;
}
