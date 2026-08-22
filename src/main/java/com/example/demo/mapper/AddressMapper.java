package com.example.demo.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.example.demo.entity.AddressEntity;
import com.example.demo.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

  @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
  Address toModel(AddressEntity entity);
}
