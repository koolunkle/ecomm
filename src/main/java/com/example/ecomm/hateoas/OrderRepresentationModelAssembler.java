package com.example.ecomm.hateoas;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.reactive.ReactiveRepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.entity.OrderEntity;
import com.example.ecomm.mapper.OrderMapper;
import com.example.ecomm.model.Order;
import com.example.ecomm.service.ItemService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class OrderRepresentationModelAssembler implements
    ReactiveRepresentationModelAssembler<OrderEntity, Order>, HateoasSupport {

  private static String serverUri = null;

  private final UserRepresentationModelAssembler uAssembler;
  private final AddressRepresentationModelAssembler aAssembler;
  private final CardRepresentationModelAssembler cAssembler;
  private final ShipmentRepresentationModelAssembler sAssembler;
  private final PaymentRepresentationModelAssembler pAssembler;

  private final ItemService itemService;

  private final OrderMapper orderMapper;

  private String getServerUri(ServerWebExchange exchange) {
    if (Strings.isBlank(serverUri)) {
      serverUri = getUriComponentBuilder(exchange).toUriString();
    }
    return serverUri;
  }

  @Override
  public Mono<Order> toModel(OrderEntity entity, ServerWebExchange exchange) {
    return Mono.just(entityToModel(entity, exchange));
  }

  public Order entityToModel(OrderEntity entity, ServerWebExchange exchange) {
    Order resource = orderMapper.toModel(entity);

    if (resource == null) {
      return new Order();
    }

    resource.customer(uAssembler.entityToModel(entity.getUserEntity(), exchange))
        .address(aAssembler.entityToModel(entity.getAddressEntity(), exchange))
        .card(cAssembler.entityToModel(entity.getCardEntity(), exchange))
        .shipment(sAssembler.entityToModel(entity.getShipment(), exchange))
        .payment(pAssembler.entityToModel(entity.getPaymentEntity(), exchange))
        .items(itemService.toModelList(entity.getItems()));

    String serverUri = getServerUri(exchange);

    resource.add(Link.of(String.format("%s/api/v1/orders", serverUri)).withRel("orders"));
    resource.add(Link.of(String.format("%s/api/v1/orders/%s", serverUri, entity.getId())).withSelfRel());

    return resource;
  }

  public Flux<Order> toListModel(Flux<OrderEntity> entities, ServerWebExchange exchange) {
    if (Objects.isNull(entities)) {
      return Flux.empty();
    }
    return Flux.from(entities.map(e -> entityToModel(e, exchange)));
  }
}
