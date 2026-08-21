package com.example.ecomm.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "users", schema = "ecomm")
public class UserEntity {

    @Id
    @Column("id")
    private UUID id;

    @NotNull(message = "User name is required.")
    @Column("username")
    private String username;

    @Column("password")
    private String password;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("email")
    private String email;

    @Column("phone")
    private String phone;

    @Column("user_status")
    private String userStatus;

    @Transient
    private Set<AddressEntity> addresses = new HashSet<>();

    @Transient
    private Set<CardEntity> cards;
}
