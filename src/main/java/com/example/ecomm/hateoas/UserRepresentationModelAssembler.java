package com.example.ecomm.hateoas;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.reactive.ReactiveRepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.entity.UserEntity;
import com.example.ecomm.mapper.UserMapper;
import com.example.ecomm.model.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class UserRepresentationModelAssembler implements
    ReactiveRepresentationModelAssembler<UserEntity, User>, HateoasSupport {

  private static String serverUri = null;

  private final UserMapper userMapper;

  private String getServerUri(ServerWebExchange exchange) {
    if (Strings.isBlank(serverUri)) {
      serverUri = getUriComponentBuilder(exchange).toUriString();
    }
    return serverUri;
  }

  @Override
  public Mono<User> toModel(UserEntity entity, ServerWebExchange exchange) {
    return Mono.just(entityToModel(entity, exchange));
  }

  public User entityToModel(UserEntity entity, ServerWebExchange exchange) {
    User resource = userMapper.toModel(entity);

    if (resource == null) {
      return new User();
    }

    String serverUri = getServerUri(exchange);

    resource.add(Link.of(String.format("%s/api/v1/customers", serverUri)).withRel("customers"));
    resource.add(Link.of(String.format("%s/api/v1/customers/%s", serverUri, entity.getId())).withSelfRel());
    resource.add(Link.of(String.format("%s/api/v1/customers/%s/addresses", serverUri, entity.getId()))
        .withRel("self_addresses"));

    return resource;
  }

  public Flux<User> toListModel(Flux<UserEntity> entities, ServerWebExchange exchange) {
    if (Objects.isNull(entities)) {
      return Flux.empty();
    }
    return Flux.from(entities.map(e -> entityToModel(e, exchange)));
  }
}
