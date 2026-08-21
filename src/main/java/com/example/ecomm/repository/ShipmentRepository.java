package com.example.ecomm.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.ecomm.entity.ShipmentEntity;

import reactor.core.publisher.Flux;

public interface ShipmentRepository extends ReactiveCrudRepository<ShipmentEntity, UUID> {

    @Query("select s.* from ecomm.orders o, ecomm.shipment s where o.shipment_id = s.id and o.id = :orderId")
    Flux<ShipmentEntity> getShipmentByOrderId(UUID orderId);
}
