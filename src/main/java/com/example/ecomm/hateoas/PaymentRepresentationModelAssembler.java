package com.example.ecomm.hateoas;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.reactive.ReactiveRepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.entity.PaymentEntity;
import com.example.ecomm.mapper.PaymentMapper;
import com.example.ecomm.model.Payment;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class PaymentRepresentationModelAssembler implements
    ReactiveRepresentationModelAssembler<PaymentEntity, Payment>, HateoasSupport {

  private static String serverUri = null;

  private final PaymentMapper paymentMapper;

  private String getServerUri(ServerWebExchange exchange) {
    if (Strings.isBlank(serverUri)) {
      serverUri = getUriComponentBuilder(exchange).toUriString();
    }
    return serverUri;
  }

  @Override
  public Mono<Payment> toModel(PaymentEntity entity, ServerWebExchange exchange) {
    return Mono.just(entityToModel(entity, exchange));
  }

  public Payment entityToModel(PaymentEntity entity, ServerWebExchange exchange) {
    Payment resource = paymentMapper.toModel(entity);

    if (resource == null) {
      return new Payment();
    }

    String serverUri = getServerUri(exchange);

    resource.add(Link.of(String.format("%s/api/v1/payments", serverUri)).withRel("payments"));
    resource.add(Link.of(String.format("%s/api/v1/payments/%s", serverUri, entity.getId())).withSelfRel());

    return resource;
  }

  public Flux<Payment> toListModel(Flux<PaymentEntity> entities, ServerWebExchange exchange) {
    if (Objects.isNull(entities)) {
      return Flux.empty();
    }
    return Flux.from(entities.map(e -> entityToModel(e, exchange)));
  }
}
