package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.AuthorizationEntity;
import com.example.demo.model.PaymentReq;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface PaymentService {

  Optional<AuthorizationEntity> authorize(@Valid PaymentReq paymentReq);

  Optional<AuthorizationEntity> getOrdersPaymentAuthorization(@NotNull String orderId);
}
