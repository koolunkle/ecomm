package com.example.demo.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
  User toModel(UserEntity entity);
}
