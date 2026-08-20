package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ItemEntity;
import com.example.demo.model.Item;

public interface ItemService {

  ItemEntity toEntity(Item m);

  List<ItemEntity> toEntityList(List<Item> items);

  Item toModel(ItemEntity e);

  List<Item> toModelList(List<ItemEntity> items);
}
