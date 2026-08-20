package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, UUID> {
}
