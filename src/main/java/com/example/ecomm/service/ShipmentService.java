package com.example.ecomm.service;

import com.example.ecomm.entity.ShipmentEntity;

import jakarta.validation.constraints.NotBlank;
import reactor.core.publisher.Flux;

public interface ShipmentService {

  Flux<ShipmentEntity> getShipmentByOrderId(@NotBlank(message = "Invalid order ID.") String id);
}
