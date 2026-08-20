package com.example.demo.service;

import com.example.demo.entity.ShipmentEntity;

import jakarta.validation.constraints.NotBlank;

public interface ShipmentService {

  Iterable<ShipmentEntity> getShipmentByOrderId(@NotBlank(message = "Invalid order ID.") String id);
}
