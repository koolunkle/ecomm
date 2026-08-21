package com.example.ecomm.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "payment", schema = "ecomm")
public class PaymentEntity {

  @Id
  @Column("id")
  private UUID id;

  @Column("authorized")
  private boolean authorized;

  @Column("message")
  private String message;
}
