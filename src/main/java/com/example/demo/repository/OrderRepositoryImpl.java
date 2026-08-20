package com.example.demo.repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.CartEntity;
import com.example.demo.entity.ItemEntity;
import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.OrderItemEntity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.NewOrder;
import com.example.demo.model.Order.StatusEnum;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepositoryExt {

        @PersistenceContext
        private EntityManager em;

        private final ItemRepository itemRepo;
        private final CartRepository cartRepo;
        private final OrderItemRepository orderItemRepo;

        @Override
        public Optional<OrderEntity> insert(NewOrder m) {
                Iterable<ItemEntity> dbItems = itemRepo.findByCustomerId(m.getCustomerId());
                List<ItemEntity> items = StreamSupport.stream(dbItems.spliterator(), false).toList();

                if (items.size() < 1) {
                        throw new ResourceNotFoundException(String.format(
                                        "There is no item found in customer's (ID: %s) cart.", m.getCustomerId()));
                }

                BigDecimal total = BigDecimal.ZERO;

                for (ItemEntity i : items) {
                        total = (BigDecimal.valueOf(i.getQuantity()).multiply(i.getPrice())).add(total);
                }

                Timestamp orderDate = Timestamp.from(Instant.now());

                em.createNativeQuery(
                                """
                                                INSERT INTO ecomm.orders (address_id, card_id, customer_id, order_date, total, status) VALUES(?, ?, ?, ?, ?, ?)
                                                """)
                                .setParameter(1, m.getAddressId())
                                .setParameter(2, m.getCardId())
                                .setParameter(3, m.getCustomerId())
                                .setParameter(4, orderDate)
                                .setParameter(5, total)
                                .setParameter(6, StatusEnum.CREATED.getValue())
                                .executeUpdate();

                Optional<CartEntity> oCart = cartRepo.findByCustomerId(UUID.fromString(m.getCustomerId()));
                CartEntity cart = oCart.orElseThrow(() -> new ResourceNotFoundException(
                                String.format("Cart not found for given customer (ID: %s)", m.getCustomerId())));

                itemRepo.deleteCartItemJoinById(cart.getItems().stream().map(ItemEntity::getId).toList(), cart.getId());

                OrderEntity entity = (OrderEntity) em.createNativeQuery(
                                """
                                                SELECT o.* FROM ecomm.orders o WHERE o.customer_id = ? AND o.order_date >= ?
                                                """,
                                OrderEntity.class)
                                .setParameter(1, m.getCustomerId())
                                .setParameter(2, OffsetDateTime.ofInstant(orderDate.toInstant(), ZoneId.of("Z"))
                                                .truncatedTo(ChronoUnit.MICROS))
                                .getSingleResult();

                orderItemRepo.saveAll(cart.getItems().stream()
                                .map(i -> new OrderItemEntity().setOrderId(entity.getId()).setItemId(i.getId()))
                                .toList());

                return Optional.of(entity);
        }
}
