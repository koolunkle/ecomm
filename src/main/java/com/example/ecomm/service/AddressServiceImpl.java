package com.example.ecomm.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.ecomm.entity.AddressEntity;
import com.example.ecomm.mapper.AddressMapper;
import com.example.ecomm.model.AddAddressReq;
import com.example.ecomm.repository.AddressRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

  private final AddressRepository repository;
  private final AddressMapper mapper;

  @Override
  public Mono<AddressEntity> createAddress(Mono<AddAddressReq> addAddressReq) {
    return addAddressReq.map(mapper::toEntity).flatMap(repository::save);
  }

  @Override
  public Mono<Void> deleteAddressesById(String id) {
    return deleteAddressesById(UUID.fromString(id));
  }

  @Override
  public Mono<Void> deleteAddressesById(UUID id) {
    return repository.deleteById(id);
  }

  @Override
  public Mono<AddressEntity> getAddressesById(String id) {
    return repository.findById(UUID.fromString(id));
  }

  @Override
  public Flux<AddressEntity> getAllAddresses() {
    return repository.findAll();
  }
}
