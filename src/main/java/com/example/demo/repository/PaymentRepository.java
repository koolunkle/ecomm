package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.PaymentEntity;

public interface PaymentRepository extends CrudRepository<PaymentEntity, UUID> {
}
