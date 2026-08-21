package com.example.ecomm.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.ecomm.entity.CartEntity;

import reactor.core.publisher.Mono;

public interface CartRepository extends ReactiveCrudRepository<CartEntity, UUID> {

    @Query("select c.* from ecomm.cart c, ecomm.users u where c.user_id = u.id and u.id = :customerId")
    Mono<CartEntity> findByCustomerId(UUID customerId);
}
