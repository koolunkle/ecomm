package com.example.ecomm.controller;

import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.ProductApi;
import com.example.ecomm.hateoas.ProductRepresentationModelAssembler;
import com.example.ecomm.model.Product;
import com.example.ecomm.service.ProductService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class ProductController implements ProductApi {

  private final ProductService service;
  private final ProductRepresentationModelAssembler assembler;

  @Override
  public Mono<ResponseEntity<Product>> getProduct(String id, ServerWebExchange exchange) {
    return service.getProduct(id)
        .map(entity -> assembler.entityToModel(entity, exchange))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<Flux<Product>>> queryProducts(
      @Nullable String tag,
      @Nullable String name,
      Integer page,
      Integer size,
      ServerWebExchange exchange) {
    return Mono.just(ResponseEntity.ok(assembler.toListModel(service.getAllProducts(), exchange)));
  }
}
