package com.example.ecomm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.AddressApi;
import com.example.ecomm.hateoas.AddressRepresentationModelAssembler;
import com.example.ecomm.model.AddAddressReq;
import com.example.ecomm.model.Address;
import com.example.ecomm.service.AddressService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class AddressController implements AddressApi {

  private final AddressService service;
  private final AddressRepresentationModelAssembler assembler;

  @Override
  public Mono<ResponseEntity<Address>> createAddress(Mono<AddAddressReq> addAddressReq,
      ServerWebExchange exchange) {
    return service.createAddress(addAddressReq)
        .map(entity -> assembler.entityToModel(entity, exchange))
        .map(model -> ResponseEntity.status(HttpStatus.CREATED).body(model));
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteAddressesById(String id, ServerWebExchange exchange) {
    return service.deleteAddressesById(id).thenReturn(ResponseEntity.accepted().build());
  }

  @Override
  public Mono<ResponseEntity<Address>> getAddressesById(String id, ServerWebExchange exchange) {
    return service.getAddressesById(id)
        .map(entity -> assembler.entityToModel(entity, exchange))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<Flux<Address>>> getAllAddresses(ServerWebExchange exchange) {
    return Mono.just(ResponseEntity.ok(assembler.toListModel(service.getAllAddresses(), exchange)));
  }
}
