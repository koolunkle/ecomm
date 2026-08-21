package com.example.ecomm.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.CartApi;
import com.example.ecomm.exception.ItemNotFoundException;
import com.example.ecomm.hateoas.CartRepresentationModelAssembler;
import com.example.ecomm.model.Cart;
import com.example.ecomm.model.Item;
import com.example.ecomm.service.CartService;
import com.example.ecomm.service.ItemService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class CartsController implements CartApi {

    private static final Logger log = LoggerFactory.getLogger(CartsController.class);
    private final CartService service;
    private final ItemService itemService;
    private final CartRepresentationModelAssembler assembler;

    @Override
    public Mono<ResponseEntity<Flux<Item>>> addCartItemsByCustomerId(String customerId, Mono<Item> item,
            ServerWebExchange exchange) {
        log.info("Request for customer ID: {}", customerId);
        return Mono.just(ResponseEntity.ok(service.addCartItemsByCustomerId(customerId, item)));
    }

    @Override
    public Mono<ResponseEntity<Flux<Item>>> addOrReplaceItemsByCustomerId(String customerId, Mono<Item> item,
            ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(service.addOrReplaceItemsByCustomerId(customerId, item)));
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteCart(String customerId, ServerWebExchange exchange) {
        return service.deleteCart(customerId).thenReturn(ResponseEntity.accepted().build());
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteItemFromCart(String customerId, String itemId,
            ServerWebExchange exchange) {
        return service.deleteItemFromCart(customerId, itemId).thenReturn(ResponseEntity.accepted().build());
    }

    @Override
    public Mono<ResponseEntity<Cart>> getCartByCustomerId(String customerId, ServerWebExchange exchange) {
        return service.getCartByCustomerId(customerId)
                .map(entity -> assembler.entityToModel(entity, exchange))
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Flux<Item>>> getCartItemsByCustomerId(String customerId, ServerWebExchange exchange) {
        return service.getCartByCustomerId(customerId)
                .map(cart -> Flux.fromIterable(itemService.toModelList(cart.getItems())))
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Item>> getCartItemsByItemId(String customerId, String itemId,
            ServerWebExchange exchange) {
        return service.getCartByCustomerId(customerId)
                .flatMap(cart -> cart.getItems().stream()
                        .filter(i -> i.getProductId().equals(UUID.fromString(itemId)))
                        .findFirst()
                        .map(itemService::toModel)
                        .map(Mono::just)
                        .orElseGet(() -> Mono.error(new ItemNotFoundException(String.format(" - %s", itemId)))))
                .map(ResponseEntity::ok);
    }
}
