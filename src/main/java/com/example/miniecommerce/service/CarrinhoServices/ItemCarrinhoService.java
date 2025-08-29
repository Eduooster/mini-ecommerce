package com.example.miniecommerce.service.CarrinhoServices;

import com.example.miniecommerce.infra.repositorie.ItemCarrinhoRepository;
import com.example.miniecommerce.web.mapper.ItemCarrinhoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCarrinhoService {

    @Autowired
    ItemCarrinhoRepository itemCarrinhoRepository;

    @Autowired
    private ItemCarrinhoMapper itemCarrinhoMapper;




}
