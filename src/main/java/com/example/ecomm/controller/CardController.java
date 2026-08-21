package com.example.ecomm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.CardApi;
import com.example.ecomm.hateoas.CardRepresentationModelAssembler;
import com.example.ecomm.model.AddCardReq;
import com.example.ecomm.model.Card;
import com.example.ecomm.service.CardService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class CardController implements CardApi {

  private final CardService service;
  private final CardRepresentationModelAssembler assembler;

  @Override
  public Mono<ResponseEntity<Void>> deleteCardById(String id, ServerWebExchange exchange) {
    return service.deleteCardById(id).thenReturn(ResponseEntity.accepted().build());
  }

  @Override
  public Mono<ResponseEntity<Flux<Card>>> getAllCards(ServerWebExchange exchange) {
    return Mono.just(ResponseEntity.ok(assembler.toListModel(service.getAllCards(), exchange)));
  }

  @Override
  public Mono<ResponseEntity<Card>> getCardById(String id, ServerWebExchange exchange) {
    return service.getCardById(id)
        .map(entity -> assembler.entityToModel(entity, exchange))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<Card>> registerCard(Mono<AddCardReq> addCardReq, ServerWebExchange exchange) {
    return service.registerCard(addCardReq)
        .map(entity -> assembler.entityToModel(entity, exchange))
        .map(model -> ResponseEntity.status(HttpStatus.CREATED).body(model));
  }
}
