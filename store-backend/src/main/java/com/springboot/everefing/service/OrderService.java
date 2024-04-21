package com.springboot.everefing.service;

import com.springboot.everefing.dto.OrderDto;
import com.springboot.everefing.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderDto addOrder(OrderDto orderDto);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getAllOrders();
    void deleteOrder(Long orderId);

    OrderDto increaseQuantity(Long orderId);
    OrderDto reduceQuantity(Long orderId);
    List<OrderDto> findByUsername(String username);
    void deleteByUsername(String username);
}
