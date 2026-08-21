package com.example.ecomm.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.example.ecomm.entity.CardEntity;
import com.example.ecomm.model.AddCardReq;
import com.example.ecomm.model.Card;

@Mapper(componentModel = "spring")
public interface CardMapper {

  @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
  @Mapping(target = "cardNumber", source = "number")
  @Mapping(target = "userId", source = "userId")
  @Mapping(target = "cvv", ignore = true)
  Card toModel(CardEntity entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "number", source = "cardNumber", qualifiedByName = "lastFourDigits")
  @Mapping(target = "userId", source = "userId")
  CardEntity toEntity(AddCardReq req);

  // Only the last 4 digits are retained; the CVV must never be persisted (PCI-DSS).
  @Named("lastFourDigits")
  default String lastFourDigits(String cardNumber) {
    if (cardNumber == null || cardNumber.length() <= 4) {
      return cardNumber;
    }
    return cardNumber.substring(cardNumber.length() - 4);
  }
}
