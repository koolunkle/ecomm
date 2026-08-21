package com.example.ecomm.hateoas;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.reactive.ReactiveRepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.entity.CardEntity;
import com.example.ecomm.mapper.CardMapper;
import com.example.ecomm.model.Card;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class CardRepresentationModelAssembler implements
    ReactiveRepresentationModelAssembler<CardEntity, Card>, HateoasSupport {

  private static String serverUri = null;

  private final CardMapper cardMapper;

  private String getServerUri(ServerWebExchange exchange) {
    if (Strings.isBlank(serverUri)) {
      serverUri = getUriComponentBuilder(exchange).toUriString();
    }
    return serverUri;
  }

  @Override
  public Mono<Card> toModel(CardEntity entity, ServerWebExchange exchange) {
    return Mono.just(entityToModel(entity, exchange));
  }

  public Card entityToModel(CardEntity entity, ServerWebExchange exchange) {
    Card resource = cardMapper.toModel(entity);

    if (resource == null) {
      return new Card();
    }

    String serverUri = getServerUri(exchange);

    resource.add(Link.of(String.format("%s/api/v1/cards", serverUri)).withRel("cards"));
    resource.add(Link.of(String.format("%s/api/v1/cards/%s", serverUri, entity.getId())).withSelfRel());

    return resource;
  }

  public Flux<Card> toListModel(Flux<CardEntity> entities, ServerWebExchange exchange) {
    if (Objects.isNull(entities)) {
      return Flux.empty();
    }
    return Flux.from(entities.map(e -> entityToModel(e, exchange)));
  }
}
