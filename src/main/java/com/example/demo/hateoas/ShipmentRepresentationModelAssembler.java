package com.example.demo.hateoas;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.demo.controller.ShipmentController;
import com.example.demo.entity.ShipmentEntity;
import com.example.demo.model.Shipment;

@Component
public class ShipmentRepresentationModelAssembler extends
    RepresentationModelAssemblerSupport<ShipmentEntity, Shipment> {

  public ShipmentRepresentationModelAssembler() {
    super(ShipmentController.class, Shipment.class);
  }

  @Override
  public Shipment toModel(ShipmentEntity entity) {
    Shipment resource = new Shipment();
    BeanUtils.copyProperties(entity, resource);

    resource.setId(entity.getId().toString());
    
    resource.add(linkTo(methodOn(ShipmentController.class).getShipmentByOrderId(entity.getId().toString()))
        .withRel("byOrderId"));

    return resource;
  }

  public List<Shipment> toListModel(Iterable<ShipmentEntity> entities) {
    if (Objects.isNull(entities)) {
      return List.of();
    }

    return StreamSupport.stream(entities.spliterator(), false).map(this::toModel).collect(toList());
  }
}
