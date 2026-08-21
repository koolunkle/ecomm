package com.example.ecomm.repository;

import com.example.ecomm.entity.OrderEntity;
import com.example.ecomm.model.NewOrder;

import reactor.core.publisher.Mono;

public interface OrderRepositoryExt {

    Mono<OrderEntity> insert(NewOrder m);
}
