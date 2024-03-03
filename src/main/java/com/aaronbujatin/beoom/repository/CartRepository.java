package com.aaronbujatin.beoom.repository;

import com.aaronbujatin.beoom.entitiy.Cart;
import com.aaronbujatin.beoom.entitiy.Product;
import com.aaronbujatin.beoom.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {


    Cart findByProductId(Long id);
    Cart findByUser(User user);

    Cart findByUserAndProduct(User user, Product product);

}
