package com.example.ecomm.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.ecomm.entity.ProductEntity;

public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, UUID> {
}
