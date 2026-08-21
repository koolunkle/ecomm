package com.example.ecomm.repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;

import com.example.ecomm.entity.ItemEntity;
import com.example.ecomm.entity.OrderEntity;
import com.example.ecomm.entity.OrderItemEntity;
import com.example.ecomm.exception.ResourceNotFoundException;
import com.example.ecomm.model.NewOrder;
import com.example.ecomm.model.Order.StatusEnum;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepositoryExt {

    private final R2dbcEntityTemplate template;
    private final ItemRepository itemRepo;
    private final CartRepository cartRepo;
    private final OrderItemRepository orderItemRepo;

    @Override
    public Mono<OrderEntity> insert(NewOrder m) {
        UUID customerId = UUID.fromString(m.getCustomerId());

        return itemRepo.findByCustomerId(customerId)
                .collectList()
                .flatMap(items -> {
                    if (items.isEmpty()) {
                        return Mono.error(new ResourceNotFoundException(String.format(
                                "There is no item found in customer's (ID: %s) cart.", m.getCustomerId())));
                    }
                    return insertOrder(m, customerId, items);
                });
    }

    private Mono<OrderEntity> insertOrder(NewOrder m, UUID customerId, List<ItemEntity> items) {
        BigDecimal total = items.stream()
                .map(i -> BigDecimal.valueOf(i.getQuantity()).multiply(i.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        OrderEntity entity = new OrderEntity();
        entity.setCustomerId(customerId);
        entity.setAddressId(UUID.fromString(m.getAddressId()));
        entity.setCardId(UUID.fromString(m.getCardId()));
        entity.setOrderDate(Timestamp.from(Instant.now()));
        entity.setTotal(total);
        entity.setStatus(StatusEnum.CREATED);

        return template.insert(OrderEntity.class).using(entity)
                .flatMap(saved -> linkItemsToOrder(saved, items, customerId)
                        .doOnSuccess(v -> saved.setItems(items))
                        .thenReturn(saved));
    }

    private Mono<Void> linkItemsToOrder(OrderEntity order, List<ItemEntity> items, UUID customerId) {
        List<OrderItemEntity> orderItems = items.stream()
                .map(i -> new OrderItemEntity().setOrderId(order.getId()).setItemId(i.getId()))
                .toList();

        return orderItemRepo.saveAll(orderItems)
                .then(cartRepo.findByCustomerId(customerId))
                .flatMap(cart -> itemRepo.deleteCartItemJoinById(
                        items.stream().map(ItemEntity::getId).toList(), cart.getId()));
    }
}
