package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.CardEntity;
import com.example.demo.model.AddCardReq;

import jakarta.validation.Valid;

public interface CardService {
  
  void deleteCardById(String id);

  Iterable<CardEntity> getAllCards();

  Optional<CardEntity> getCardById(String id);

  Optional<CardEntity> registerCard(@Valid AddCardReq addCardReq);
}
