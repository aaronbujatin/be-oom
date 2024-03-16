package com.aaronbujatin.beoom.repository;

import com.aaronbujatin.beoom.entitiy.Cart;
import com.aaronbujatin.beoom.entitiy.Product;
import com.aaronbujatin.beoom.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {


    Cart findByProductId(Long id);
    List<Cart> findByUser(User user);

    Cart findByUserAndProduct(User user, Product product);

    // Add method to delete carts by user
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.user = :user")
    void deleteByUser(@Param("user") User user);

}
