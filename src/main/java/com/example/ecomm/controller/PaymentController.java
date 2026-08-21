package com.example.ecomm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.PaymentApi;
import com.example.ecomm.mapper.AuthorizationMapper;
import com.example.ecomm.model.Authorization;
import com.example.ecomm.service.PaymentService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class PaymentController implements PaymentApi {

  private final PaymentService service;
  private final AuthorizationMapper mapper;

  @Override
  public Mono<ResponseEntity<Authorization>> getOrdersPaymentAuthorization(String id, ServerWebExchange exchange) {
    return service.getOrdersPaymentAuthorization(id)
        .map(mapper::toModel)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
