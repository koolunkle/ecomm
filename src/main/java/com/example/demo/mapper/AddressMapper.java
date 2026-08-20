package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.entity.AddressEntity;
import com.example.demo.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

  Address toModel(AddressEntity entity);
}
