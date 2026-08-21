package com.example.ecomm.entity;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "shipment", schema = "ecomm")
public class ShipmentEntity {

  @Id
  @Column("id")
  private UUID id;

  @Column("est_delivery_date")
  private Timestamp estDeliveryDate;

  @Column("carrier")
  private String carrier;
}
