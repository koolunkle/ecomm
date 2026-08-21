package com.example.ecomm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.OrderApi;
import com.example.ecomm.hateoas.OrderRepresentationModelAssembler;
import com.example.ecomm.model.NewOrder;
import com.example.ecomm.model.Order;
import com.example.ecomm.service.OrderService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class OrderController implements OrderApi {

  private final OrderRepresentationModelAssembler assembler;
  private final OrderService service;

  @Override
  public Mono<ResponseEntity<Order>> addOrder(Mono<NewOrder> newOrder, ServerWebExchange exchange) {
    return newOrder.flatMap(service::addOrder)
        .map(entity -> assembler.entityToModel(entity, exchange))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<Flux<Order>>> getOrdersByCustomerId(String customerId, ServerWebExchange exchange) {
    return Mono.just(ResponseEntity.ok(assembler.toListModel(service.getOrdersByCustomerId(customerId), exchange)));
  }

  @Override
  public Mono<ResponseEntity<Order>> getOrdersByOrderId(String id, ServerWebExchange exchange) {
    return service.getByOrderId(id)
        .map(entity -> assembler.entityToModel(entity, exchange))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
