package com.aaronbujatin.beoom.controller;

import com.aaronbujatin.beoom.dto.CartDto;
import com.aaronbujatin.beoom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> addToCart(@RequestBody CartDto cartDto){
        CartDto response = cartService.addToCart(cartDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
