package com.example.ecomm.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Table("ecomm.product")
public class ProductEntity {

  @Id
  @Column("id")
  private UUID id;

  @NotNull(message = "Product name is required.")
  @Column("name")
  private String name;

  @Column("description")
  private String description;

  @Column("price")
  private BigDecimal price;

  @Column("count")
  private int count;

  @Column("image_url")
  private String imageUrl;

  @Transient
  private List<TagEntity> tags = new ArrayList<>();
}
