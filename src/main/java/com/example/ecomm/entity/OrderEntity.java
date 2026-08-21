package com.example.ecomm.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.example.ecomm.model.Order.StatusEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of = "id")
@Table("ecomm.orders")
public class OrderEntity {

  @Id
  @Column("id")
  private UUID id;

  @Column("customer_id")
  private UUID customerId;

  @Column("address_id")
  private UUID addressId;

  @Column("card_id")
  private UUID cardId;

  @Column("order_date")
  private Timestamp orderDate;

  @Column("total")
  private BigDecimal total;

  @Column("payment_id")
  private UUID paymentId;

  @Column("shipment_id")
  private UUID shipmentId;

  @Column("status")
  private StatusEnum status;

  @Transient
  private UserEntity userEntity;

  @Transient
  private AddressEntity addressEntity;

  @Transient
  private PaymentEntity paymentEntity;

  @Transient
  private ShipmentEntity shipment;

  @Transient
  private CardEntity cardEntity;

  @Transient
  private List<ItemEntity> items = new ArrayList<>();
}
