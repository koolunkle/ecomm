package com.example.ecomm.mapper;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.example.ecomm.entity.OrderEntity;
import com.example.ecomm.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

  @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
  @Mapping(target = "customer", ignore = true)
  @Mapping(target = "address", ignore = true)
  @Mapping(target = "card", ignore = true)
  @Mapping(target = "items", ignore = true)
  @Mapping(target = "date", source = "orderDate")
  @Mapping(target = "payment", ignore = true)
  @Mapping(target = "shipment", ignore = true)
  Order toModel(OrderEntity entity);

  default OffsetDateTime map(Timestamp timestamp) {
    return timestamp == null ? null : timestamp.toInstant().atOffset(ZoneOffset.UTC);
  }
}
