package com.example.ecomm.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.ecomm.entity.CartEntity;
import com.example.ecomm.entity.ItemEntity;
import com.example.ecomm.entity.UserEntity;
import com.example.ecomm.exception.CustomerNotFoundException;
import com.example.ecomm.exception.GenericAlreadyExistsException;
import com.example.ecomm.exception.ItemNotFoundException;
import com.example.ecomm.model.Item;
import com.example.ecomm.repository.CartRepository;
import com.example.ecomm.repository.ItemRepository;
import com.example.ecomm.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Validated
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository repository;
  private final UserRepository userRepo;
  private final ItemRepository itemRepo;
  private final ItemService itemService;

  @Override
  public Flux<Item> addCartItemsByCustomerId(String customerId, Mono<Item> newItem) {
    return newItem.flatMap(item -> getCartByCustomerId(customerId).flatMap(cart -> {
      boolean exists = cart.getItems().stream()
          .anyMatch(i -> i.getProductId().equals(UUID.fromString(item.getId())));

      if (exists) {
        return Mono.error(new GenericAlreadyExistsException(String.format(
            "Item with Id (%s) already exists. You can update it.", item.getId())));
      }

      return itemRepo.save(itemService.toEntity(item))
          .flatMap(saved -> itemRepo.saveMapping(cart.getId(), saved.getId()));
    })).thenMany(getCartItemsByCustomerId(customerId));
  }

  @Override
  public Flux<Item> addOrReplaceItemsByCustomerId(String customerId, Mono<Item> newItem) {
    return newItem.flatMap(item -> getCartByCustomerId(customerId).flatMap(cart -> {
      ItemEntity existing = cart.getItems().stream()
          .filter(i -> i.getProductId().equals(UUID.fromString(item.getId())))
          .findFirst()
          .orElse(null);

      if (existing != null) {
        existing.setQuantity(item.getQuantity()).setPrice(existing.getPrice());
        return itemRepo.save(existing).then();
      }

      return itemRepo.save(itemService.toEntity(item))
          .flatMap(saved -> itemRepo.saveMapping(cart.getId(), saved.getId()));
    })).thenMany(getCartItemsByCustomerId(customerId));
  }

  @Override
  public Mono<Void> deleteCart(String customerId) {
    return getCartByCustomerId(customerId).flatMap(cart -> repository.deleteById(cart.getId()));
  }

  @Override
  public Mono<Void> deleteItemFromCart(String customerId, String itemId) {
    return getCartByCustomerId(customerId).flatMap(cart -> {
      List<UUID> ids = cart.getItems().stream()
          .filter(i -> i.getProductId().equals(UUID.fromString(itemId)))
          .map(ItemEntity::getId)
          .toList();

      if (ids.isEmpty()) {
        return Mono.error(new ItemNotFoundException(String.format(" - %s", itemId)));
      }

      return itemRepo.deleteCartItemJoinById(ids, cart.getId());
    });
  }

  @Override
  public Mono<CartEntity> getCartByCustomerId(String customerId) {
    UUID uuid = UUID.fromString(customerId);
    Mono<CartEntity> cart = repository.findByCustomerId(uuid).defaultIfEmpty(new CartEntity());
    Mono<List<ItemEntity>> items = itemRepo.findByCustomerId(uuid).collectList();
    Mono<UserEntity> user = userRepo.findById(uuid)
        .switchIfEmpty(Mono.error(new CustomerNotFoundException(String.format(" - %s", customerId))));

    return Mono.zip(cart, user, items).map(t -> {
      CartEntity entity = t.getT1();
      entity.setUser(t.getT2());
      entity.setItems(t.getT3());
      return entity;
    });
  }

  private Flux<Item> getCartItemsByCustomerId(String customerId) {
    return getCartByCustomerId(customerId)
        .flatMapMany(cart -> Flux.fromIterable(itemService.toModelList(cart.getItems())));
  }
}
