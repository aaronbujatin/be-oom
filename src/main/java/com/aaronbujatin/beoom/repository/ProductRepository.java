package com.aaronbujatin.beoom.repository;

import com.aaronbujatin.beoom.entitiy.Product;
import com.aaronbujatin.beoom.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category);



}
