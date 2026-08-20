package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.entity.CardEntity;
import com.example.demo.model.Card;

@Mapper(componentModel = "spring")
public interface CardMapper {

  @Mapping(target = "cardNumber", source = "number")
  @Mapping(target = "userId", source = "user.id")
  @Mapping(target = "cvv", ignore = true)
  Card toModel(CardEntity entity);
}
