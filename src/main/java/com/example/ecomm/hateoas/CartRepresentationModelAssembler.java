package com.example.ecomm.hateoas;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.reactive.ReactiveRepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.entity.CartEntity;
import com.example.ecomm.mapper.CartMapper;
import com.example.ecomm.model.Cart;
import com.example.ecomm.service.ItemService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class CartRepresentationModelAssembler implements
    ReactiveRepresentationModelAssembler<CartEntity, Cart>, HateoasSupport {

  private static String serverUri = null;

  private final ItemService itemService;

  private final CartMapper cartMapper;

  private String getServerUri(ServerWebExchange exchange) {
    if (Strings.isBlank(serverUri)) {
      serverUri = getUriComponentBuilder(exchange).toUriString();
    }
    return serverUri;
  }

  @Override
  public Mono<Cart> toModel(CartEntity entity, ServerWebExchange exchange) {
    return Mono.just(entityToModel(entity, exchange));
  }

  public Cart entityToModel(CartEntity entity, ServerWebExchange exchange) {
    Cart resource = cartMapper.toModel(entity);

    if (resource == null) {
      return new Cart();
    }

    resource.items(itemService.toModelList(entity.getItems()));

    String serverUri = getServerUri(exchange);

    resource.add(Link.of(String.format("%s/api/v1/carts/%s", serverUri, entity.getId())).withSelfRel());

    return resource;
  }

  public Flux<Cart> toListModel(Flux<CartEntity> entities, ServerWebExchange exchange) {
    if (Objects.isNull(entities)) {
      return Flux.empty();
    }
    return Flux.from(entities.map(e -> entityToModel(e, exchange)));
  }
}
