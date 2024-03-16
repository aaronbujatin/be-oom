package com.aaronbujatin.beoom.repository;

import com.aaronbujatin.beoom.entitiy.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
