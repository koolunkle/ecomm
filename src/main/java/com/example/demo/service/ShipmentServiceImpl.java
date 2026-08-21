package com.example.demo.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demo.entity.ShipmentEntity;
import com.example.demo.repository.ShipmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

  private final ShipmentRepository repository;

  @Override
  public Iterable<ShipmentEntity> getShipmentByOrderId(String id) {
    return repository.getShipmentByOrderId(UUID.fromString(id));
  }
}
