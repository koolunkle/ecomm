package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.OrderEntity;

public interface OrderRepository extends CrudRepository<OrderEntity, UUID>, OrderRepositoryExt {

    @Query("select o from OrderEntity o join o.userEntity u where u.id = :customerId")
    public Iterable<OrderEntity> findByCustomerId(@Param("customerId") UUID customerId);
}
