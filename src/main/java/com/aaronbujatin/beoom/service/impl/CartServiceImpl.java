package com.aaronbujatin.beoom.service.impl;

import com.aaronbujatin.beoom.dto.CartDto;
import com.aaronbujatin.beoom.entitiy.Product;
import com.aaronbujatin.beoom.entitiy.User;
import com.aaronbujatin.beoom.exception.ProductOutOfStockException;
import com.aaronbujatin.beoom.exception.ResourceNotFoundException;
import com.aaronbujatin.beoom.repository.CartRepository;
import com.aaronbujatin.beoom.repository.ProductRepository;
import com.aaronbujatin.beoom.service.CartService;
import com.aaronbujatin.beoom.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.aaronbujatin.beoom.entitiy.Cart;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final UserAuthenticationService userAuthenticationService;

    @Override
    public CartDto addToCart(CartDto cartRequest) {
        User user = userAuthenticationService.getAuthenticatedUser();
        Cart userCart = cartRepository.findByUser(user);

        Product product = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product "+ cartRequest.getProductId() + " not found"));

        Cart myCart = cartRepository.findByUserAndProduct(user, product);

        //check if user already have a cart
       if(userCart == null){
           Cart cart = new Cart();
           cart.setQuantity(cart.getQuantity());
           cart.setUser(user);

       }




        return null;
    }
}
