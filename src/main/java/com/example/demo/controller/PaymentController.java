package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.PaymentApi;
import com.example.demo.model.Authorization;
import com.example.demo.model.PaymentReq;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PaymentController implements PaymentApi {

  @Override
  public ResponseEntity<Authorization> authorize(PaymentReq paymentReq) {
    return null;
  }

  @Override
  public ResponseEntity<Authorization> getOrdersPaymentAuthorization(String id) {
    return null;
  }
}
