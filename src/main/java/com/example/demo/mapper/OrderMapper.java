package com.example.demo.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.example.demo.entity.OrderEntity;
import com.example.demo.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

  @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
  @Mapping(target = "customer", ignore = true)
  @Mapping(target = "address", ignore = true)
  @Mapping(target = "card", ignore = true)
  @Mapping(target = "items", ignore = true)
  @Mapping(target = "date", ignore = true)
  @Mapping(target = "total", ignore = true)
  @Mapping(target = "payment", ignore = true)
  @Mapping(target = "shipment", ignore = true)
  Order toModel(OrderEntity entity);
}
