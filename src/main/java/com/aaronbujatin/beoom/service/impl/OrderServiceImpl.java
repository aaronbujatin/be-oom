package com.aaronbujatin.beoom.service.impl;

import com.aaronbujatin.beoom.dto.OrderDto;
import com.aaronbujatin.beoom.entitiy.*;
import com.aaronbujatin.beoom.enums.OrderStatus;
import com.aaronbujatin.beoom.exception.ResourceNotFoundException;
import com.aaronbujatin.beoom.repository.*;
import com.aaronbujatin.beoom.service.OrderService;
import com.aaronbujatin.beoom.service.UserAuthenticationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final UserAuthenticationService userAuthenticationService;
    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public Order createOrder(OrderDto orderDto) {
        User user = userAuthenticationService.getAuthenticatedUser();
        List<Cart> cart = cartRepository.findByUser(user);

        if(cart.isEmpty()){
            throw new ResourceNotFoundException("Cart cannot be found or empty");
        }

        Order order = new Order();
        order.setCreatedAt(LocalDate.now());
        BigDecimal cartAmount = calculateTotalAmount(cart);
        order.setTotalAmount(cartAmount);
        order.setOrderStatus(OrderStatus.PENDING);

        Set<OrderItem> orderItems = cart.stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setOrder(order);
                    orderItem.setProduct(cartItem.getProduct());
                    return orderItem;
                }
        ).collect(Collectors.toSet());
        order.setOrderItems(orderItems);
        order.setUser(user);

        Address address = new Address();
        address.setCity(orderDto.getCity());
        address.setState(orderDto.getState());
        address.setZip(orderDto.getZip());
        address.setCountry(orderDto.getCountry());
        address.setPhone(orderDto.getPhone());
        address.setUser(user);
        addressRepository.save(address);
        cartRepository.deleteByUser(user);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderByUserId(Long id) {
        return null;
    }

    @Override
    public BigDecimal calculateTotalAmount(List<Cart> carts) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Cart cart : carts) {
            BigDecimal productPrice = cart.getProduct().getPrice();
            int quantity = cart.getQuantity();
            BigDecimal cartTotal = productPrice.multiply(BigDecimal.valueOf(quantity));
            totalAmount = totalAmount.add(cartTotal);
        }
        return totalAmount;
    }
}
