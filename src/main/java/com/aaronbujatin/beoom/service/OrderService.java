package com.aaronbujatin.beoom.service;


import com.aaronbujatin.beoom.dto.OrderDto;
import com.aaronbujatin.beoom.entitiy.Cart;
import com.aaronbujatin.beoom.entitiy.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    Order createOrder(OrderDto orderDto);
    List<Order> getOrderByUserId(Long id);
    BigDecimal calculateTotalAmount(List<Cart> cart);

}
