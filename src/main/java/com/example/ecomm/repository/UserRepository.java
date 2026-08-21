package com.example.ecomm.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.ecomm.entity.AddressEntity;
import com.example.ecomm.entity.UserEntity;

import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<UserEntity, UUID> {

    @Query("select a.* from ecomm.users u, ecomm.address a, ecomm.user_address ua where u.id = ua.user_id and a.id = ua.address_id and u.id = :customerId")
    Flux<AddressEntity> getAddressesByCustomerId(UUID customerId);
}
