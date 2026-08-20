package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.AddressEntity;
import com.example.demo.entity.CardEntity;
import com.example.demo.entity.UserEntity;

public interface UserService {
  
  void deleteCustomerById(String id);

  Optional<Iterable<AddressEntity>> getAddressesByCustomerId(String id);

  Iterable<UserEntity> getAllCustomers();

  Optional<CardEntity> getCardByCustomerId(String id);

  Optional<UserEntity> getCustomerById(String id);
}
