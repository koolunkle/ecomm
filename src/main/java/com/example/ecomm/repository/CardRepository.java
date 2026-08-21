package com.example.ecomm.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.ecomm.entity.CardEntity;

import reactor.core.publisher.Mono;

public interface CardRepository extends ReactiveCrudRepository<CardEntity, UUID> {

    Mono<CardEntity> findByUserId(UUID userId);
}
