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
@Table("ecomm.authorizations")
public class AuthorizationEntity {

    @Id
    @Column("id")
    private UUID id;

    @Column("order_id")
    private UUID orderId;

    @Column("authorized")
    private boolean authorized;

    @Column("time")
    private Timestamp time;

    @Column("message")
    private String message;

    @Column("error")
    private String error;
}
