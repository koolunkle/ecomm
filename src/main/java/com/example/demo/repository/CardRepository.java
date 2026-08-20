package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.CardEntity;

public interface CardRepository extends CrudRepository<CardEntity, UUID> {
}
