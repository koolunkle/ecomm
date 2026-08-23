package com.example.ecomm.service;

import java.util.Optional;

import com.example.ecomm.entity.AddressEntity;
import com.example.ecomm.entity.CardEntity;
import com.example.ecomm.entity.UserEntity;

public interface UserService {
  
  void deleteCustomerById(String id);

  Optional<Iterable<AddressEntity>> getAddressesByCustomerId(String id);

  Iterable<UserEntity> getAllCustomers();

  Optional<CardEntity> getCardByCustomerId(String id);

  Optional<UserEntity> getCustomerById(String id);
}
