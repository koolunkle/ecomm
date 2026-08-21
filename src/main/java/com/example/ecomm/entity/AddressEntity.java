package com.example.ecomm.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
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
@Table("ecomm.address")
public class AddressEntity {

    @Id
    @Column("id")
    private UUID id;

    @Column("number")
    private String number;

    @Column("residency")
    private String residency;

    @Column("street")
    private String street;

    @Column("city")
    private String city;

    @Column("state")
    private String state;

    @Column("country")
    private String country;

    @Column("pincode")
    private String pincode;
}
