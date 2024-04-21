package com.springboot.everefing.service.impl;

import com.springboot.everefing.dto.OrderDto;
import com.springboot.everefing.entity.Order;
import com.springboot.everefing.exception.ResourceNotFoundException;
import com.springboot.everefing.repository.OrderRepository;
import com.springboot.everefing.service.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ModelMapper modelMapper;

    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        order.setQuantity(1);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDto.class);
    }

    @Override
    public OrderDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Not found order with id: " + orderId)
        );
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public List<OrderDto> getAllOrders() {

        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Not found order with id: " + orderId)
        );
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderDto increaseQuantity(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Not found order with id: " + orderId)
        );

        order.setQuantity(order.getQuantity() + 1);

        Order updateOrder = orderRepository.save(order);

        return modelMapper.map(updateOrder, OrderDto.class);
    }

    @Override
    public OrderDto reduceQuantity(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Not found order with id: " + orderId)
        );

        order.setQuantity(order.getQuantity() - 1);

        Order updateOrder = orderRepository.save(order);

        return modelMapper.map(updateOrder, OrderDto.class);
    }


    @Override
    public List<OrderDto> findByUsername(String username) {
        List<Order> orders = orderRepository.findByUsername(username);
        return orders.stream().map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByUsername(String username) {
        orderRepository.deleteByUsername(username);
    }
}
