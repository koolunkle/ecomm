package com.example.ecomm.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.ecomm.entity.TagEntity;

import reactor.core.publisher.Flux;

public interface TagRepository extends ReactiveCrudRepository<TagEntity, UUID> {

    @Query("select t.* from ecomm.product p, ecomm.tag t, ecomm.product_tag pt where p.id = :productId and p.id = pt.product_id and t.id = pt.tag_id")
    Flux<TagEntity> findTagsByProductId(UUID productId);
}
