package com.example.ecomm.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.ecomm.entity.OrderEntity;

import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<OrderEntity, UUID>, OrderRepositoryExt {

    Flux<OrderEntity> findByCustomerId(UUID customerId);
}
