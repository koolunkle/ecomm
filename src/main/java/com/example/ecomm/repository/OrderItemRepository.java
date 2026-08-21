package com.example.ecomm.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.ecomm.entity.OrderItemEntity;

public interface OrderItemRepository extends ReactiveCrudRepository<OrderItemEntity, UUID> {
}
