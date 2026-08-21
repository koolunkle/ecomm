package com.example.ecomm.service;

import org.springframework.validation.annotation.Validated;

import com.example.ecomm.entity.ProductEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
public interface ProductService {

  @NotNull
  Flux<ProductEntity> getAllProducts();

  Mono<ProductEntity> getProduct(@NotBlank(message = "Invalid product ID.") String id);
}
