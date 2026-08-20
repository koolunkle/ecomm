package com.example.demo.service;

import java.util.Optional;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CardEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.AddCardReq;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

  private final CardRepository repository;
  private final UserRepository userRepo;

  @Override
  public void deleteCardById(String id) {
    repository.deleteById(UUID.fromString(id));
  }

  @Override
  public Iterable<CardEntity> getAllCards() {
    return repository.findAll();
  }

  @Override
  public Optional<CardEntity> getCardById(String id) {
    return repository.findById(UUID.fromString(id));
  }

  @Override
  public Optional<CardEntity> registerCard(@Valid AddCardReq addCardReq) {
    return Optional.of(repository.save(toEntity(addCardReq)));
  }

  private CardEntity toEntity(AddCardReq m) {
    CardEntity e = new CardEntity();
    Optional<UserEntity> user = userRepo.findById(UUID.fromString(m.getUserId()));

    user.ifPresent(e::setUser);

    return e.setNumber(m.getCardNumber()).setCvv(m.getCvv())
        .setExpires(m.getExpires());
  }
}
