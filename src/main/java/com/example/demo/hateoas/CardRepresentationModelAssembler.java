package com.example.demo.hateoas;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.demo.controller.CardController;
import com.example.demo.entity.CardEntity;
import com.example.demo.model.Card;

@Component
public class CardRepresentationModelAssembler extends
    RepresentationModelAssemblerSupport<CardEntity, Card> {

  public CardRepresentationModelAssembler() {
    super(CardController.class, Card.class);
  }

  @Override
  public Card toModel(CardEntity entity) {
    String uid = Objects.nonNull(entity.getUser()) ? entity.getUser().getId().toString() : null;
    Card resource = new Card();
    BeanUtils.copyProperties(entity, resource);

    resource
        .id(entity.getId().toString())
        .cardNumber(entity.getNumber())
        .cvv(entity.getCvv())
        .expires(entity.getExpires())
        .userId(uid);
        
    resource.add(linkTo(methodOn(CardController.class).getCardById(entity.getId().toString())).withSelfRel());

    return resource;
  }

  public List<Card> toListModel(Iterable<CardEntity> entities) {
    if (Objects.isNull(entities)) {
      return List.of();
    }

    return StreamSupport.stream(entities.spliterator(), false).map(this::toModel).collect(toList());
  }
}
