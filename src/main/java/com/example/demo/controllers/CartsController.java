package com.example.demo.controllers;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.CartApi;
import com.example.demo.model.Cart;
import com.example.demo.model.Item;

import jakarta.validation.Valid;

@RestController
public class CartsController implements CartApi {

    private static final Logger log = LoggerFactory.getLogger(CartsController.class);

    @Override
    public ResponseEntity<List<Item>> addCartItemsByCustomerId(String custormerId, @Valid Item item) {
        log.info("고객 ID 요청: {}\nItem: {}", custormerId, item);
        return ResponseEntity.ok(Collections.emptyList());
    }

    @Override
    public ResponseEntity<List<Cart>> getCartByCustomerId(String customerId) {
        throw new RuntimeException("수동 예외 발생 (Manual Exception thrown");
    }
}
