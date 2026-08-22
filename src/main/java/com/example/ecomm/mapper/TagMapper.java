package com.example.ecomm.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.example.ecomm.entity.TagEntity;
import com.example.ecomm.model.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {

  @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
  Tag toModel(TagEntity entity);
}
