package com.example.demo.hateoas;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.demo.controller.CardController;
import com.example.demo.entity.CardEntity;
import com.example.demo.mapper.CardMapper;
import com.example.demo.model.Card;

@Component
public class CardRepresentationModelAssembler extends
    RepresentationModelAssemblerSupport<CardEntity, Card> {

  private final CardMapper cardMapper;

  public CardRepresentationModelAssembler(CardMapper cardMapper) {
    super(CardController.class, Card.class);
    this.cardMapper = cardMapper;
  }

  @Override
  public Card toModel(CardEntity entity) {
    Card resource = cardMapper.toModel(entity);

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
