package com.example.demo.hateoas;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.demo.controller.OrderController;
import com.example.demo.entity.OrderEntity;
import com.example.demo.model.Order;
import com.example.demo.service.ItemService;

@Component
public class OrderRepresentationModelAssembler extends
    RepresentationModelAssemblerSupport<OrderEntity, Order> {

  private final UserRepresentationModelAssembler uAssembler;
  private final AddressRepresentationModelAssembler aAssembler;
  private final CardRepresentationModelAssembler cAssembler;
  private final ItemService itemService;

  public OrderRepresentationModelAssembler(UserRepresentationModelAssembler uAssembler,
      AddressRepresentationModelAssembler aAssembler, CardRepresentationModelAssembler cAssembler,
      ShipmentRepresentationModelAssembler sAssembler, ItemService itemService) {
    super(OrderController.class, Order.class);
    this.uAssembler = uAssembler;
    this.aAssembler = aAssembler;
    this.cAssembler = cAssembler;
    this.itemService = itemService;
  }

  @Override
  public Order toModel(OrderEntity entity) {
    Order resource = new Order();
    BeanUtils.copyProperties(entity, resource);

    resource
        .id(entity.getId().toString())
        .customer(uAssembler.toModel(entity.getUserEntity()))
        .address(aAssembler.toModel(entity.getAddressEntity()))
        .card(cAssembler.toModel(entity.getCardEntity()))
        .items(itemService.toModelList(entity.getItems()))
        .date(entity.getOrderDate().toInstant().atOffset(ZoneOffset.UTC));

    resource.add(linkTo(methodOn(OrderController.class).getOrdersByOrderId(entity.getId().toString())).withSelfRel());

    return resource;
  }

  public List<Order> toListModel(Iterable<OrderEntity> entities) {
    if (Objects.isNull(entities)) {
      return List.of();
    }

    return StreamSupport.stream(entities.spliterator(), false).map(this::toModel)
        .collect(toList());
  }
}
