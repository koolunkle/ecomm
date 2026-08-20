package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.ShipmentEntity;

public interface ShipmentRepository extends CrudRepository<ShipmentEntity, UUID> {
}
