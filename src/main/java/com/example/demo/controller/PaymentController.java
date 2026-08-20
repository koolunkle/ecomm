package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.PaymentApi;
import com.example.demo.model.Authorization;
import com.example.demo.model.PaymentReq;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PaymentController implements PaymentApi {

  @Override
  public ResponseEntity<Authorization> authorize(@Valid PaymentReq paymentReq) {
    return null;
  }

  @Override
  public ResponseEntity<Authorization> getOrdersPaymentAuthorization(@NotNull @Valid String id) {
    return null;
  }
}
