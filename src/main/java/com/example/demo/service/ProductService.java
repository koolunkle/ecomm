package com.example.demo.service;

import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import com.example.demo.entity.ProductEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Validated
public interface ProductService {

  @NotNull
  Iterable<ProductEntity> getAllProducts();

  Optional<ProductEntity> getProduct(@NotBlank(message = "Invalid product ID.") String id);
}