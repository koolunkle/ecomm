package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User toModel(UserEntity entity);
}
