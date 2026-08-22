package com.example.demo.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.example.demo.entity.PaymentEntity;
import com.example.demo.model.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

  @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
  Payment toModel(PaymentEntity entity);
}
