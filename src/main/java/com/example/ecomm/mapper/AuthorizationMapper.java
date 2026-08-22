package com.example.ecomm.mapper;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.example.ecomm.entity.AuthorizationEntity;
import com.example.ecomm.model.Authorization;

@Mapper(componentModel = "spring")
public interface AuthorizationMapper {

  @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
  Authorization toModel(AuthorizationEntity entity);

  default OffsetDateTime map(Timestamp timestamp) {
    return timestamp == null ? null : timestamp.toInstant().atOffset(ZoneOffset.UTC);
  }
}
