package com.example.ecomm.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.ecomm.entity.ProductEntity;
import com.example.ecomm.repository.ProductRepository;
import com.example.ecomm.repository.TagRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;
  private final TagRepository tagRepo;

  @Override
  public Flux<ProductEntity> getAllProducts() {
    return repository.findAll().flatMap(this::withTags);
  }

  @Override
  public Mono<ProductEntity> getProduct(String id) {
    return repository.findById(UUID.fromString(id)).flatMap(this::withTags);
  }

  private Mono<ProductEntity> withTags(ProductEntity product) {
    return tagRepo.findTagsByProductId(product.getId())
        .collectList()
        .map(tags -> {
          product.setTags(tags);
          return product;
        });
  }
}
