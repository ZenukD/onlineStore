package com.springboot.everefing.service.impl;

import com.springboot.everefing.dto.OrderDto;
import com.springboot.everefing.entity.Order;
import com.springboot.everefing.exception.ResourceNotFoundException;
import com.springboot.everefing.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOrder() {
        OrderDto orderDto = new OrderDto();
        Order order = new Order();
        when(modelMapper.map(orderDto, Order.class)).thenReturn(order);
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(modelMapper.map(order, OrderDto.class)).thenReturn(orderDto);

        OrderDto savedOrderDto = orderService.addOrder(orderDto);

        assertNotNull(savedOrderDto);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testGetOrder() {
        Order order = new Order();
        OrderDto orderDto = new OrderDto();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(modelMapper.map(order, OrderDto.class)).thenReturn(orderDto);

        OrderDto foundOrderDto = orderService.getOrder(1L);

        assertNotNull(foundOrderDto);
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testGetOrder_NotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.getOrder(1L));
    }

    @Test
    void testGetAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        when(orderRepository.findAll()).thenReturn(orders);
        when(modelMapper.map(any(Order.class), eq(OrderDto.class))).thenReturn(new OrderDto());

        List<OrderDto> orderDtos = orderService.getAllOrders();

        assertNotNull(orderDtos);
        assertEquals(1, orderDtos.size());
    }

    @Test
    void testDeleteOrder() {
        Order order = new Order();
        order.setId(1L);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).deleteById(1L);
    }



    @Test
    void testFindByUsername() {
        String username = "testuser";
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        when(orderRepository.findByUsername(username)).thenReturn(orders);
        when(modelMapper.map(any(Order.class), eq(OrderDto.class))).thenReturn(new OrderDto());

        List<OrderDto> orderDtos = orderService.findByUsername(username);

        assertNotNull(orderDtos);
        assertEquals(1, orderDtos.size());
    }

    @Test
    void testDeleteByUsername() {
        String username = "testuser";
        orderService.deleteByUsername(username);
        verify(orderRepository, times(1)).deleteByUsername(username);
    }
}
