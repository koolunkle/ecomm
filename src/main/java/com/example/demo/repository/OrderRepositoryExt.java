package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.entity.OrderEntity;
import com.example.demo.model.NewOrder;

public interface OrderRepositoryExt {

    Optional<OrderEntity> insert(NewOrder m);
}
