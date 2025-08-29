package com.example.miniecommerce.web.mapper;

import com.example.miniecommerce.domain.ItemCarrinho;
import com.example.miniecommerce.domain.Produto;
import com.example.miniecommerce.web.dto.in.ItemCarrinhoDto;

import com.example.miniecommerce.web.dto.out.ItemListDto;
import com.example.miniecommerce.web.dto.out.ItensCarrinhoDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemCarrinhoMapper {


    ItemCarrinho toDomain(ItemCarrinhoDto itemCarrinhoDto);



    List<ItemListDto> toCarrinhoResponseDetail(List<ItemCarrinho> ItemCarrinho);


    List<ItensCarrinhoDetail> toDtoList(List<ItemCarrinho> itens);


}
