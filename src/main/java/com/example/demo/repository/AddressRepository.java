package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, UUID> {
}
