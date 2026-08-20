package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(target = "tag", ignore = true)
  Product toModel(ProductEntity entity);
}
