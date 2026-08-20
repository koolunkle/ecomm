package com.example.demo.controller;

import static org.springframework.http.ResponseEntity.notFound;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.OrderApi;
import com.example.demo.hateoas.OrderRepresentationModelAssembler;
import com.example.demo.model.NewOrder;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class OrderController implements OrderApi {

  private final OrderRepresentationModelAssembler assembler;
  private final OrderService service;

  @Override
  public ResponseEntity<Order> addOrder(NewOrder newOrder) {
    return service.addOrder(newOrder)
        .map(assembler::toModel)
        .map(ResponseEntity::ok)
        .orElse(notFound().build());
  }

  @Override
  public ResponseEntity<List<Order>> getOrdersByCustomerId(String customerId) {
    return ResponseEntity.ok(assembler.toListModel(service.getOrdersByCustomerId(customerId)));
  }

  @Override
  public ResponseEntity<Order> getOrdersByOrderId(String id) {
    return service.getByOrderId(id)
        .map(assembler::toModel)
        .map(ResponseEntity::ok)
        .orElse(notFound().build());
  }
}
