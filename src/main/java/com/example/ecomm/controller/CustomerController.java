package com.example.ecomm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.CustomerApi;
import com.example.ecomm.hateoas.AddressRepresentationModelAssembler;
import com.example.ecomm.hateoas.CardRepresentationModelAssembler;
import com.example.ecomm.hateoas.UserRepresentationModelAssembler;
import com.example.ecomm.model.Address;
import com.example.ecomm.model.Card;
import com.example.ecomm.model.User;
import com.example.ecomm.service.UserService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class CustomerController implements CustomerApi {

  private final UserRepresentationModelAssembler assembler;
  private final AddressRepresentationModelAssembler addrAssembler;
  private final CardRepresentationModelAssembler cardAssembler;
  private final UserService service;

  @Override
  public Mono<ResponseEntity<Void>> deleteCustomerById(String id, ServerWebExchange exchange) {
    return service.deleteCustomerById(id).thenReturn(ResponseEntity.accepted().build());
  }

  @Override
  public Mono<ResponseEntity<Flux<Address>>> getAddressesByCustomerId(String id, ServerWebExchange exchange) {
    return Mono.just(ResponseEntity.ok(addrAssembler.toListModel(service.getAddressesByCustomerId(id), exchange)));
  }

  @Override
  public Mono<ResponseEntity<Flux<User>>> getAllCustomers(ServerWebExchange exchange) {
    return Mono.just(ResponseEntity.ok(assembler.toListModel(service.getAllCustomers(), exchange)));
  }

  @Override
  public Mono<ResponseEntity<Card>> getCardsByCustomerId(String id, ServerWebExchange exchange) {
    return service.getCardByCustomerId(id)
        .map(entity -> cardAssembler.entityToModel(entity, exchange))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<User>> getCustomerById(String id, ServerWebExchange exchange) {
    return service.getCustomerById(id)
        .map(entity -> assembler.entityToModel(entity, exchange))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
