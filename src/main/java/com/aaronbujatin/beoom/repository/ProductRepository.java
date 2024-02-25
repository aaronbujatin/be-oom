package com.aaronbujatin.beoom.repository;

import com.aaronbujatin.beoom.entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
