package com.example.demo.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demo.entity.AuthorizationEntity;
import com.example.demo.model.PaymentReq;
import com.example.demo.repository.AuthorizationRepository;

import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final AuthorizationRepository authorizationRepo;

  @Override
  public Optional<AuthorizationEntity> authorize(PaymentReq paymentReq) {
    return Optional.empty();
  }

  @Override
  public Optional<AuthorizationEntity> getOrdersPaymentAuthorization(String orderId) {
    return authorizationRepo.findByOrderEntityId(UUID.fromString(orderId));
  }
}
