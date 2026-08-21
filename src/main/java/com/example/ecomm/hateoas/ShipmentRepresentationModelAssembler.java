package com.example.ecomm.hateoas;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.reactive.ReactiveRepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.entity.ShipmentEntity;
import com.example.ecomm.mapper.ShipmentMapper;
import com.example.ecomm.model.Shipment;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class ShipmentRepresentationModelAssembler implements
    ReactiveRepresentationModelAssembler<ShipmentEntity, Shipment>, HateoasSupport {

  private static String serverUri = null;

  private final ShipmentMapper shipmentMapper;

  private String getServerUri(ServerWebExchange exchange) {
    if (Strings.isBlank(serverUri)) {
      serverUri = getUriComponentBuilder(exchange).toUriString();
    }
    return serverUri;
  }

  @Override
  public Mono<Shipment> toModel(ShipmentEntity entity, ServerWebExchange exchange) {
    return Mono.just(entityToModel(entity, exchange));
  }

  public Shipment entityToModel(ShipmentEntity entity, ServerWebExchange exchange) {
    Shipment resource = shipmentMapper.toModel(entity);

    if (resource == null) {
      return new Shipment();
    }

    String serverUri = getServerUri(exchange);

    resource.add(Link.of(String.format("%s/api/v1/shipping/%s", serverUri, entity.getId())).withSelfRel());

    return resource;
  }

  public Flux<Shipment> toListModel(Flux<ShipmentEntity> entities, ServerWebExchange exchange) {
    if (Objects.isNull(entities)) {
      return Flux.empty();
    }
    return Flux.from(entities.map(e -> entityToModel(e, exchange)));
  }
}
