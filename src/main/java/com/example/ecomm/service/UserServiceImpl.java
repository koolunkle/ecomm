package com.example.ecomm.service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.ecomm.entity.AddressEntity;
import com.example.ecomm.entity.CardEntity;
import com.example.ecomm.entity.UserEntity;
import com.example.ecomm.exception.CustomerNotFoundException;
import com.example.ecomm.exception.ResourceNotFoundException;
import com.example.ecomm.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Override
  public void deleteCustomerById(String id) {
    repository.deleteById(UUID.fromString(id));
  }

  @Override
  public Optional<Iterable<AddressEntity>> getAddressesByCustomerId(String id) {
    return repository.findById(UUID.fromString(id)).map(UserEntity::getAddresses);
  }

  @Override
  public Iterable<UserEntity> getAllCustomers() {
    return repository.findAll();
  }

  @Override
  public Optional<CardEntity> getCardByCustomerId(String id) {
    Set<CardEntity> cards = repository.findById(UUID.fromString(id))
        .map(UserEntity::getCards)
        .orElseThrow(() -> new CustomerNotFoundException(String.format(" - %s", id)));

    if (cards.isEmpty()) {
      throw new ResourceNotFoundException(String.format("No card found for customer (ID: %s)", id));
    }

    return cards.stream().findFirst();
  }

  @Override
  public Optional<UserEntity> getCustomerById(String id) {
    return repository.findById(UUID.fromString(id));
  }
}
