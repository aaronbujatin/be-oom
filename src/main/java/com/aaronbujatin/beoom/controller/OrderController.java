package com.aaronbujatin.beoom.controller;

import com.aaronbujatin.beoom.dto.OrderDto;
import com.aaronbujatin.beoom.entitiy.Order;
import com.aaronbujatin.beoom.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto){
        Order order = orderService.createOrder(orderDto);
        return new ResponseEntity<>("Successfully ordered", HttpStatus.CREATED);
    }

}
