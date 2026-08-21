package com.example.ecomm.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.ecomm.entity.AuthorizationEntity;
import com.example.ecomm.model.PaymentReq;
import com.example.ecomm.repository.AuthorizationRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@Validated
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final AuthorizationRepository authorizationRepo;

  @Override
  public Mono<AuthorizationEntity> authorize(Mono<PaymentReq> paymentReq) {
    return Mono.empty();
  }

  @Override
  public Mono<AuthorizationEntity> getOrdersPaymentAuthorization(String orderId) {
    return authorizationRepo.findByOrderEntityId(UUID.fromString(orderId));
  }
}
