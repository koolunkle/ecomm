package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.OrderItemEntity;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, UUID> {
}
