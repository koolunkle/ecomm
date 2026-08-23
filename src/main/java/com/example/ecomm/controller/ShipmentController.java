package com.example.ecomm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecomm.ShippingApi;
import com.example.ecomm.hateoas.ShipmentRepresentationModelAssembler;
import com.example.ecomm.model.Shipment;
import com.example.ecomm.service.ShipmentService;

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
