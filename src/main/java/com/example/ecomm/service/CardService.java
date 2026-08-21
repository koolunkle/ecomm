package com.example.ecomm.service;

import java.util.UUID;

import com.example.ecomm.entity.CardEntity;
import com.example.ecomm.model.AddCardReq;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CardService {

  Mono<Void> deleteCardById(String id);

  Mono<Void> deleteCardById(UUID id);

  Flux<CardEntity> getAllCards();

  Mono<CardEntity> getCardById(String id);

  Mono<CardEntity> registerCard(@Valid Mono<AddCardReq> addCardReq);

  CardEntity toEntity(AddCardReq model);
}
