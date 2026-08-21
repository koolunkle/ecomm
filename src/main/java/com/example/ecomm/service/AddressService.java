package com.example.ecomm.service;

import java.util.UUID;

import com.example.ecomm.entity.AddressEntity;
import com.example.ecomm.model.AddAddressReq;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AddressService {

  Mono<AddressEntity> createAddress(Mono<AddAddressReq> addAddressReq);

  Mono<Void> deleteAddressesById(String id);

  Mono<Void> deleteAddressesById(UUID id);

  Mono<AddressEntity> getAddressesById(String id);

  Flux<AddressEntity> getAllAddresses();
}
