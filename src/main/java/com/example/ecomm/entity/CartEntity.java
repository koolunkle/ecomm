package com.example.ecomm.entity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "cart", schema = "ecomm")
public class CartEntity {

    @Id
    @Column("id")
    private UUID id;

    @Column("user_id")
    private UUID userId;

    @Transient
    private UserEntity user;

    @Transient
    private List<ItemEntity> items = Collections.emptyList();
}
