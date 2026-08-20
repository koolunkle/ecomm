package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.entity.CartEntity;
import com.example.demo.model.Cart;

@Mapper(componentModel = "spring")
public interface CartMapper {

  @Mapping(target = "customerId", source = "user.id")
  @Mapping(target = "items", ignore = true)
  Cart toModel(CartEntity entity);
}
