package com.example.demo.service;

import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demo.entity.OrderEntity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.NewOrder;
import com.example.demo.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository repository;

  @Override
  public Optional<OrderEntity> addOrder(NewOrder newOrder) {
    if (Strings.isEmpty(newOrder.getCustomerId())) {
      throw new ResourceNotFoundException("Invalid customer id.");
    }

    if (Strings.isEmpty(newOrder.getAddressId())) {
      throw new ResourceNotFoundException("Invalid address id.");
    }

    if (Strings.isEmpty(newOrder.getCardId())) {
      throw new ResourceNotFoundException("Invalid card id.");
    }

    return repository.insert(newOrder);
  }

  @Override
  public Iterable<OrderEntity> getOrdersByCustomerId(String customerId) {
    return repository.findByCustomerId(UUID.fromString(customerId));
  }

  @Override
  public Optional<OrderEntity> getByOrderId(String id) {
    return repository.findById(UUID.fromString(id));
  }
}
