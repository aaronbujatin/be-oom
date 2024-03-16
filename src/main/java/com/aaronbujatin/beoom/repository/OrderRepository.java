package com.aaronbujatin.beoom.repository;

import com.aaronbujatin.beoom.entitiy.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
