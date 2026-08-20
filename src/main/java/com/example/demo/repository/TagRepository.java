package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.TagEntity;

public interface TagRepository extends CrudRepository<TagEntity, UUID> {
}
