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
@Table(name = "order_item", schema = "ecomm")
public class OrderItemEntity {

  @Id
  @Column("id")
  private UUID id;

  @Column("order_id")
  private UUID orderId;

  @Column("item_id")
  private UUID itemId;
}
