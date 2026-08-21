package com.example.ecomm.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.ecomm.entity.AddressEntity;
import com.example.ecomm.entity.CardEntity;
import com.example.ecomm.entity.UserEntity;
import com.example.ecomm.exception.CustomerNotFoundException;
import com.example.ecomm.exception.ResourceNotFoundException;
import com.example.ecomm.repository.CardRepository;
import com.example.ecomm.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final CardRepository cardRepo;

  @Override
  public Mono<Void> deleteCustomerById(String id) {
    return deleteCustomerById(UUID.fromString(id));
  }

  @Override
  public Mono<Void> deleteCustomerById(UUID id) {
    return repository.deleteById(id);
  }

  @Override
  public Flux<AddressEntity> getAddressesByCustomerId(String id) {
    return repository.getAddressesByCustomerId(UUID.fromString(id));
  }

  @Override
  public Flux<UserEntity> getAllCustomers() {
    return repository.findAll();
  }

  @Override
  public Mono<CardEntity> getCardByCustomerId(String id) {
    UUID uuid = UUID.fromString(id);

    return repository.findById(uuid)
        .switchIfEmpty(Mono.error(new CustomerNotFoundException(String.format(" - %s", id))))
        .flatMap(user -> cardRepo.findByUserId(uuid))
        .switchIfEmpty(Mono.error(
            new ResourceNotFoundException(String.format("No card found for customer (ID: %s)", id))));
  }

  @Override
  public Mono<UserEntity> getCustomerById(String id) {
    return repository.findById(UUID.fromString(id));
  }
}
