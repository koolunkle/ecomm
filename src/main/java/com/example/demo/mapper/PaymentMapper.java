package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.entity.PaymentEntity;
import com.example.demo.model.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

  Payment toModel(PaymentEntity entity);
}
