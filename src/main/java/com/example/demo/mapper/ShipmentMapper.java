package com.example.demo.mapper;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.example.demo.entity.ShipmentEntity;
import com.example.demo.model.Shipment;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {

  @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
  @Mapping(target = "orderId", ignore = true)
  @Mapping(target = "trackingNumber", ignore = true)
  Shipment toModel(ShipmentEntity entity);

  default LocalDate map(Timestamp timestamp) {
    return timestamp == null ? null : timestamp.toLocalDateTime().toLocalDate();
  }
}
