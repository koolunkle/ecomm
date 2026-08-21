package com.example.ecomm.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.example.ecomm.entity.AddressEntity;
import com.example.ecomm.model.AddAddressReq;
import com.example.ecomm.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

  @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
  Address toModel(AddressEntity entity);

  @Mapping(target = "id", ignore = true)
  AddressEntity toEntity(AddAddressReq req);
}
