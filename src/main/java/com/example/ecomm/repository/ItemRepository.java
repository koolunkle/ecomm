package com.example.ecomm.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.ecomm.entity.ItemEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemRepository extends ReactiveCrudRepository<ItemEntity, UUID> {

    @Query("select i.* from ecomm.cart c, ecomm.item i, ecomm.users u, ecomm.cart_item ci where u.id = :customerId and c.user_id = u.id and c.id = ci.cart_id and i.id = ci.item_id")
    Flux<ItemEntity> findByCustomerId(UUID customerId);

    @Query("delete from ecomm.cart_item where item_id in (:ids) and cart_id = :cartId")
    Mono<Void> deleteCartItemJoinById(List<UUID> ids, UUID cartId);

    @Query("delete from ecomm.item where id in (:ids)")
    Mono<Void> deleteByIds(List<UUID> ids);

    @Query("insert into ecomm.cart_item(cart_id, item_id) values(:cartId, :itemId)")
    Mono<Void> saveMapping(UUID cartId, UUID itemId);
}
