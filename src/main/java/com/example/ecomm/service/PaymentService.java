package com.example.ecomm.service;

import com.example.ecomm.entity.AuthorizationEntity;
import com.example.ecomm.model.PaymentReq;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Mono;

public interface PaymentService {

  Mono<AuthorizationEntity> authorize(@Valid Mono<PaymentReq> paymentReq);

  Mono<AuthorizationEntity> getOrdersPaymentAuthorization(@NotNull String orderId);
}
