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

import com.example.demo.controller.AddressController;
import com.example.demo.entity.AddressEntity;
import com.example.demo.model.Address;

@Component
public class AddressRepresentationModelAssembler extends
    RepresentationModelAssemblerSupport<AddressEntity, Address> {

  public AddressRepresentationModelAssembler() {
    super(AddressController.class, Address.class);
  }

  @Override
  public Address toModel(AddressEntity entity) {
    Address resource = new Address();
    BeanUtils.copyProperties(entity, resource);

    resource.setId(entity.getId().toString());
    
    resource.add(linkTo(methodOn(AddressController.class).getAddressesById(entity.getId().toString())).withSelfRel());

    return resource;
  }

  public List<Address> toListModel(Iterable<AddressEntity> entities) {
    if (Objects.isNull(entities)) {
      return List.of();
    }

    return StreamSupport.stream(entities.spliterator(), false).map(this::toModel)
        .collect(toList());
  }
}
