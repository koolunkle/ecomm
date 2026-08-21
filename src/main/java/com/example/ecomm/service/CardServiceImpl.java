package com.example.ecomm.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.ecomm.entity.CardEntity;
import com.example.ecomm.mapper.CardMapper;
import com.example.ecomm.model.AddCardReq;
import com.example.ecomm.repository.CardRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Validated
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

  private final CardRepository repository;
  private final CardMapper mapper;

  @Override
  public Mono<Void> deleteCardById(String id) {
    return deleteCardById(UUID.fromString(id));
  }

  @Override
  public Mono<Void> deleteCardById(UUID id) {
    return repository.deleteById(id);
  }

  @Override
  public Flux<CardEntity> getAllCards() {
    return repository.findAll();
  }

  @Override
  public Mono<CardEntity> getCardById(String id) {
    return repository.findById(UUID.fromString(id));
  }

  @Override
  public Mono<CardEntity> registerCard(Mono<AddCardReq> addCardReq) {
    return addCardReq.map(this::toEntity).flatMap(repository::save);
  }

  @Override
  public CardEntity toEntity(AddCardReq model) {
    return mapper.toEntity(model);
  }
}
