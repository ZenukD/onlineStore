package com.springboot.everefing.controller;

import com.springboot.everefing.controller.OrderController;
import com.springboot.everefing.dto.OrderDto;
import com.springboot.everefing.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddOrder_Success() {
        OrderDto orderDto = new OrderDto();
        when(orderService.addOrder(any(OrderDto.class))).thenReturn(orderDto);

        ResponseEntity<OrderDto> response = orderController.addOrder(orderDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderDto, response.getBody());
    }

    @Test
    public void testGetOrder() {
        Long orderId = 1L;
        OrderDto orderDto = new OrderDto();
        when(orderService.getOrder(orderId)).thenReturn(orderDto);

        ResponseEntity<OrderDto> response = orderController.getOrder(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderDto, response.getBody());
    }


    @Test
    public void testDeleteOrder() {
        Long orderId = 1L;
        doNothing().when(orderService).deleteOrder(orderId);

        ResponseEntity<String> response = orderController.deleteOrder(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order deleted successfully!", response.getBody());
    }


    @Test
    public void testFindByUsername() {
        String username = "testuser";
        OrderDto orderDto = new OrderDto();
        List<OrderDto> orderDtos = List.of(orderDto);
        when(orderService.findByUsername(username)).thenReturn(orderDtos);

        ResponseEntity<List<OrderDto>> response = orderController.findByUsername(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderDtos, response.getBody());
    }

    @Test
    public void testDeleteOrdersByUsername() {
        String username = "testuser";
        doNothing().when(orderService).deleteByUsername(username);

        ResponseEntity<String> response = orderController.deleteOrdersByUsername(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Orders deleted successfully!", response.getBody());
    }

}
