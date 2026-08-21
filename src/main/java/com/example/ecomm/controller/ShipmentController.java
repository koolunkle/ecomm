package com.example.ecomm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.ShippingApi;
import com.example.ecomm.hateoas.ShipmentRepresentationModelAssembler;
import com.example.ecomm.model.Shipment;
import com.example.ecomm.service.ShipmentService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class ShipmentController implements ShippingApi {

  private final ShipmentService service;
  private final ShipmentRepresentationModelAssembler assembler;

  @Override
  public Mono<ResponseEntity<Flux<Shipment>>> getShipmentByOrderId(String id, ServerWebExchange exchange) {
    return Mono.just(ResponseEntity.ok(assembler.toListModel(service.getShipmentByOrderId(id), exchange)));
  }
}
