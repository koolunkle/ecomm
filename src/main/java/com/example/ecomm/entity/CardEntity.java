package com.example.ecomm.entity;

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
@Table("ecomm.card")
public class CardEntity {

    @Id
    @Column("id")
    private UUID id;

    @Column("number")
    private String number;

    @Column("expires")
    private String expires;

    @Column("user_id")
    private UUID userId;

    @Transient
    private UserEntity user;
}
