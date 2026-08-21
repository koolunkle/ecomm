package com.example.ecomm.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table("ecomm.tag")
public class TagEntity {

  @Id
  @Column("id")
  private UUID id;

  @NotNull(message = "Product name is required.")
  @Column("name")
  private String name;
}
