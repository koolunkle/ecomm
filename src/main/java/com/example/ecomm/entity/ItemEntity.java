package com.example.ecomm.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
@Table("ecomm.item")
public class ItemEntity {

    @Id
    @Column("id")
    private UUID id;

    @Column("product_id")
    private UUID productId;

    @Transient
    private ProductEntity product;

    @Column("unit_price")
    private BigDecimal price;

    @Column("quantity")
    private int quantity;
}
