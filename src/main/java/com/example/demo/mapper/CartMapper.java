package com.example.demo.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.example.demo.entity.CartEntity;
import com.example.demo.model.Cart;

@Mapper(componentModel = "spring")
public interface CartMapper {

  @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
  @Mapping(target = "customerId", source = "user.id")
  @Mapping(target = "items", ignore = true)
  Cart toModel(CartEntity entity);
}
