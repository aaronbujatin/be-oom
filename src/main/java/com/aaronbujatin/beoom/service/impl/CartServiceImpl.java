package com.aaronbujatin.beoom.service.impl;

import com.aaronbujatin.beoom.dto.CartDto;
import com.aaronbujatin.beoom.entitiy.User;
import com.aaronbujatin.beoom.repository.CartRepository;
import com.aaronbujatin.beoom.repository.ProductRepository;
import com.aaronbujatin.beoom.repository.UserRepository;
import com.aaronbujatin.beoom.service.CartService;
import com.aaronbujatin.beoom.service.UserAuthenticationService;
import jakarta.transaction.Transactional;
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
    private final UserRepository userRepository;

    @Transactional
    @Override
    public CartDto addToCart(CartDto cartRequest) {
        User user = userAuthenticationService.getAuthenticatedUser();
        List<Cart> userCart = cartRepository.findByUser(user);

        Optional<Cart> productInCart = userCart.stream()
                .filter(cart -> cart.getProduct().getId().equals(cartRequest.getProduct().getId()))
                .findFirst();

        if (productInCart.isPresent()) {
            // Product already in the cart, update the quantity
            Cart cart = productInCart.get();
            cart.setQuantity(cart.getQuantity() + cartRequest.getQuantity());
            cartRepository.save(cart);
            log.info("Updated quantity for product: {}", cart.getProduct().getName());
        } else {
            // Product not in the cart, create a new cart item
            try {
                Cart cart = new Cart();
                cart.setProduct(cartRequest.getProduct());
                cart.setQuantity(cartRequest.getQuantity());
                cart.setUser(user);
                cartRepository.save(cart);
                log.info("Added product to the cart: {}", cart.getProduct().getName());
            } catch (Exception e) {
                // Handle the exception, e.g., log an error or return an error message
                log.error("Error adding product to the cart: {}", e.getMessage());
                // Optionally, you can rethrow the exception if needed
                throw e;
            }
        }

        //You might want to return something here, for example, the updated cart or a confirmation message
        return cartRequest;

    }
}
