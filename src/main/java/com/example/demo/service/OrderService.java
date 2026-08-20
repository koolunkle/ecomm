package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.OrderEntity;
import com.example.demo.model.NewOrder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface OrderService {

  Optional<OrderEntity> addOrder(@Valid NewOrder newOrder);

  Iterable<OrderEntity> getOrdersByCustomerId(@NotNull @Valid String customerId);

  Optional<OrderEntity> getByOrderId(String id);
}
