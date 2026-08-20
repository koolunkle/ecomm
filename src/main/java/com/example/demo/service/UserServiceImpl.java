package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AddressEntity;
import com.example.demo.entity.CardEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;

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
    List<CardEntity> cards = repository.findById(UUID.fromString(id))
        .map(UserEntity::getCards)
        .orElseThrow(() -> new CustomerNotFoundException(String.format(" - %s", id)));

    if (cards.isEmpty()) {
      throw new ResourceNotFoundException(String.format("No card found for customer (ID: %s)", id));
    }

    return Optional.of(cards.get(0));
  }

  @Override
  public Optional<UserEntity> getCustomerById(String id) {
    return repository.findById(UUID.fromString(id));
  }
}
