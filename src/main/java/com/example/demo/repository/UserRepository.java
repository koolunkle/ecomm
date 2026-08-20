package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
}
