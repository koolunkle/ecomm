package com.example.ecomm.service;

import com.example.ecomm.entity.OrderEntity;
import com.example.ecomm.model.NewOrder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

  Mono<OrderEntity> addOrder(@Valid NewOrder newOrder);

  Flux<OrderEntity> getOrdersByCustomerId(@NotNull @Valid String customerId);

  Mono<OrderEntity> getByOrderId(String id);
}
