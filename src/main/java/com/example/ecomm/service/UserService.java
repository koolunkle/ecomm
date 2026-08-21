package com.example.ecomm.service;

import java.util.UUID;

import com.example.ecomm.entity.AddressEntity;
import com.example.ecomm.entity.CardEntity;
import com.example.ecomm.entity.UserEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

  Mono<Void> deleteCustomerById(String id);

  Mono<Void> deleteCustomerById(UUID id);

  Flux<AddressEntity> getAddressesByCustomerId(String id);

  Flux<UserEntity> getAllCustomers();

  Mono<CardEntity> getCardByCustomerId(String id);

  Mono<UserEntity> getCustomerById(String id);
}
