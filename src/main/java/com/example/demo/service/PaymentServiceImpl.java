package com.example.demo.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demo.entity.AuthorizationEntity;
import com.example.demo.entity.OrderEntity;
import com.example.demo.model.PaymentReq;
import com.example.demo.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final OrderRepository orderRepo;

  @Override
  public Optional<AuthorizationEntity> authorize(PaymentReq paymentReq) {
    return Optional.empty();
  }

  @Override
  public Optional<AuthorizationEntity> getOrdersPaymentAuthorization(String orderId) {
    return orderRepo.findById(UUID.fromString(orderId)).map(OrderEntity::getAuthorizationEntity);
  }
}
