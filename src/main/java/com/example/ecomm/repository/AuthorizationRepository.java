package com.example.ecomm.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.ecomm.entity.AuthorizationEntity;

import reactor.core.publisher.Mono;

public interface AuthorizationRepository extends ReactiveCrudRepository<AuthorizationEntity, UUID> {

    @Query("select * from ecomm.authorizations where order_id = :orderId")
    Mono<AuthorizationEntity> findByOrderEntityId(UUID orderId);
}
