package com.springboot.everefing.controller;

import com.springboot.everefing.dto.OrderDto;
import com.springboot.everefing.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto) {
        OrderDto savedOrder = orderService.addOrder(orderDto);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Long orderId) {
        OrderDto orderDto = orderService.getOrder(orderId);
        return ResponseEntity.ok(orderDto);
    }

//    @GetMapping
//    public ResponseEntity<List<OrderDto>> getAllOrders(){
//        List<OrderDto> orders = orderService.getAllOrders();
//        return ResponseEntity.ok(orders);
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order deleted successfully!");
    }

    @PatchMapping("{id}/increase")
    public ResponseEntity<OrderDto> increaseQuantity(@PathVariable("id") Long orderId) {
        OrderDto orderDto = orderService.increaseQuantity(orderId);
        return ResponseEntity.ok(orderDto);
    }

    @PatchMapping("{id}/reduce")
    public ResponseEntity<OrderDto> reduceQuantity(@PathVariable("id") Long orderId) {
        OrderDto orderDto = orderService.reduceQuantity(orderId);
        return ResponseEntity.ok(orderDto);
    }


    @GetMapping("{username}/findByUsername")
    public ResponseEntity<List<OrderDto>> findByUsername(@PathVariable("username") String username) {
        List<OrderDto> orderDtos = orderService.findByUsername(username);
        return ResponseEntity.ok(orderDtos);
    }

    @DeleteMapping("{username}/deleteByUsername")
    public ResponseEntity<String> deleteOrdersByUsername(@PathVariable("username") String username) {
        orderService.deleteByUsername(username);
        return ResponseEntity.ok("Orders deleted successfully!");
    }
}
