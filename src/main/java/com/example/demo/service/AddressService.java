package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.AddressEntity;
import com.example.demo.model.AddAddressReq;

public interface AddressService {
  
  Optional<AddressEntity> createAddress(AddAddressReq addAddressReq);

  void deleteAddressesById(String id);

  Optional<AddressEntity> getAddressesById(String id);

  Iterable<AddressEntity> getAllAddresses();
}
