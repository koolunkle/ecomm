package com.example.ecomm.hateoas;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.reactive.ReactiveRepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.entity.AddressEntity;
import com.example.ecomm.mapper.AddressMapper;
import com.example.ecomm.model.Address;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class AddressRepresentationModelAssembler
    implements ReactiveRepresentationModelAssembler<AddressEntity, Address>, HateoasSupport {

  private static String serverUri = null;

  private final AddressMapper addressMapper;

  @Override
  public Mono<Address> toModel(AddressEntity entity, ServerWebExchange exchange) {
    return Mono.just(entityToModel(entity, exchange));
  }

  private String getServerUri(ServerWebExchange exchange) {
    if (Strings.isBlank(serverUri)) {
      serverUri = getUriComponentBuilder(exchange).toUriString();
    }
    return serverUri;
  }

  public Address entityToModel(AddressEntity entity, ServerWebExchange exchange) {
    Address resource = addressMapper.toModel(entity);

    if (resource == null) {
      return new Address();
    }

    String serverUri = getServerUri(exchange);

    resource.add(Link.of(String.format("%s/api/v1/addresses", serverUri)).withRel("addresses"));
    resource.add(Link.of(String.format("%s/api/v1/addresses/%s", serverUri, entity.getId())).withSelfRel());

    return resource;
  }

  public Flux<Address> toListModel(Flux<AddressEntity> entities, ServerWebExchange exchange) {
    if (Objects.isNull(entities)) {
      return Flux.empty();
    }
    return Flux.from(entities.map(e -> entityToModel(e, exchange)));
  }
}
