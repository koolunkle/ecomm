package com.example.ecomm.service;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.ecomm.entity.AddressEntity;
import com.example.ecomm.entity.CardEntity;
import com.example.ecomm.entity.ItemEntity;
import com.example.ecomm.entity.OrderEntity;
import com.example.ecomm.entity.PaymentEntity;
import com.example.ecomm.entity.ShipmentEntity;
import com.example.ecomm.entity.UserEntity;
import com.example.ecomm.exception.ResourceNotFoundException;
import com.example.ecomm.model.NewOrder;
import com.example.ecomm.repository.AddressRepository;
import com.example.ecomm.repository.CardRepository;
import com.example.ecomm.repository.ItemRepository;
import com.example.ecomm.repository.OrderRepository;
import com.example.ecomm.repository.PaymentRepository;
import com.example.ecomm.repository.ShipmentRepository;
import com.example.ecomm.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Validated
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository repository;
  private final UserRepository userRepo;
  private final AddressRepository addressRepo;
  private final CardRepository cardRepo;
  private final ShipmentRepository shipmentRepo;
  private final PaymentRepository paymentRepo;
  private final ItemRepository itemRepo;

  @Override
  public Mono<OrderEntity> addOrder(NewOrder newOrder) {
    if (Strings.isEmpty(newOrder.getCustomerId())) {
      throw new ResourceNotFoundException("Invalid customer id.");
    }

    if (Strings.isEmpty(newOrder.getAddressId())) {
      throw new ResourceNotFoundException("Invalid address id.");
    }

    if (Strings.isEmpty(newOrder.getCardId())) {
      throw new ResourceNotFoundException("Invalid card id.");
    }

    return repository.insert(newOrder);
  }

  @Override
  public Flux<OrderEntity> getOrdersByCustomerId(String customerId) {
    return repository.findByCustomerId(UUID.fromString(customerId)).flatMap(this::populateAssociations);
  }

  @Override
  public Mono<OrderEntity> getByOrderId(String id) {
    return repository.findById(UUID.fromString(id)).flatMap(this::populateAssociations);
  }

  private Mono<OrderEntity> populateAssociations(OrderEntity order) {
    Mono<UserEntity> user = userRepo.findById(order.getCustomerId()).defaultIfEmpty(new UserEntity());
    Mono<AddressEntity> address = addressRepo.findById(order.getAddressId()).defaultIfEmpty(new AddressEntity());
    Mono<CardEntity> card = (order.getCardId() != null ? cardRepo.findById(order.getCardId()) : Mono.<CardEntity>empty())
        .defaultIfEmpty(new CardEntity());
    Mono<ShipmentEntity> shipment = (order.getShipmentId() != null ? shipmentRepo.findById(order.getShipmentId())
        : Mono.<ShipmentEntity>empty()).defaultIfEmpty(new ShipmentEntity());
    Mono<PaymentEntity> payment = (order.getPaymentId() != null ? paymentRepo.findById(order.getPaymentId())
        : Mono.<PaymentEntity>empty()).defaultIfEmpty(new PaymentEntity());
    Mono<List<ItemEntity>> items = itemRepo.findByOrderId(order.getId()).collectList();

    return Mono.zip(user, address, card, shipment, payment, items).map(t -> {
      order.setUserEntity(t.getT1());
      order.setAddressEntity(t.getT2());
      order.setCardEntity(t.getT3());
      order.setShipment(t.getT4());
      order.setPaymentEntity(t.getT5());
      order.setItems(t.getT6());
      return order;
    });
  }
}
