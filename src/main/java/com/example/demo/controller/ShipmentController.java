package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ShippingApi;
import com.example.demo.hateoas.ShipmentRepresentationModelAssembler;
import com.example.demo.model.Shipment;
import com.example.demo.service.ShipmentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ShipmentController implements ShippingApi {

  private final ShipmentService service;
  private final ShipmentRepresentationModelAssembler assembler;

  @Override
  public ResponseEntity<List<Shipment>> getShipmentByOrderId(String id) {
    return ResponseEntity.ok(assembler.toListModel(service.getShipmentByOrderId(id)));
  }
}
