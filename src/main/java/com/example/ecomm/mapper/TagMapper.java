package com.example.ecomm.mapper;

import org.mapstruct.Mapper;

import com.example.ecomm.entity.TagEntity;
import com.example.ecomm.model.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {

  Tag toModel(TagEntity entity);
}
