package com.example.ecomm.hateoas;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.reactive.ReactiveRepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.example.ecomm.entity.ProductEntity;
import com.example.ecomm.mapper.ProductMapper;
import com.example.ecomm.model.Product;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class ProductRepresentationModelAssembler implements
    ReactiveRepresentationModelAssembler<ProductEntity, Product>, HateoasSupport {

  private static String serverUri = null;

  private final ProductMapper productMapper;

  private String getServerUri(ServerWebExchange exchange) {
    if (Strings.isBlank(serverUri)) {
      serverUri = getUriComponentBuilder(exchange).toUriString();
    }
    return serverUri;
  }

  @Override
  public Mono<Product> toModel(ProductEntity entity, ServerWebExchange exchange) {
    return Mono.just(entityToModel(entity, exchange));
  }

  public Product entityToModel(ProductEntity entity, ServerWebExchange exchange) {
    Product resource = productMapper.toModel(entity);

    if (resource == null) {
      return new Product();
    }

    String serverUri = getServerUri(exchange);

    resource.add(Link.of(String.format("%s/api/v1/products", serverUri)).withRel("products"));
    resource.add(Link.of(String.format("%s/api/v1/products/%s", serverUri, entity.getId())).withSelfRel());

    return resource;
  }

  public Flux<Product> toListModel(Flux<ProductEntity> entities, ServerWebExchange exchange) {
    if (Objects.isNull(entities)) {
      return Flux.empty();
    }
    return Flux.from(entities.map(e -> entityToModel(e, exchange)));
  }
}
