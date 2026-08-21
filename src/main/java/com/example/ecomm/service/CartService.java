package com.example.ecomm.service;

import com.example.ecomm.entity.CartEntity;
import com.example.ecomm.model.Item;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CartService {

  Flux<Item> addCartItemsByCustomerId(String customerId, @Valid Mono<Item> item);

  Flux<Item> addOrReplaceItemsByCustomerId(String customerId, @Valid Mono<Item> item);

  Mono<Void> deleteCart(String customerId);

  Mono<Void> deleteItemFromCart(String customerId, String itemId);

  Mono<CartEntity> getCartByCustomerId(String customerId);
}
