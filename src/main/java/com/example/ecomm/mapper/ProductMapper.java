package com.example.ecomm.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.example.ecomm.entity.ProductEntity;
import com.example.ecomm.model.Product;

@Mapper(componentModel = "spring", uses = TagMapper.class)
public interface ProductMapper {

  @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
  @Mapping(target = "tag", source = "tags")
  Product toModel(ProductEntity entity);
}
