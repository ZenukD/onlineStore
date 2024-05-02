package com.springboot.everefing.entity;

import com.springboot.everefing.entity.Order;
import com.springboot.everefing.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class OrderTest {

    @Test
    void testConstructor() {
        // Arrange
        Long id = 1L;
        Product product = new Product();
        String username = "testUser";
        int quantity = 2;
        LocalDateTime createdTime = LocalDateTime.now();

        // Act
        Order order = new Order(id, product, username, quantity, createdTime);

        // Assert
        assertNotNull(order);
        assertEquals(id, order.getId());
        assertEquals(product, order.getProduct());
        assertEquals(username, order.getUsername());
        assertEquals(quantity, order.getQuantity());
        assertEquals(createdTime, order.getCreatedTime());
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        Order order = new Order();
        Long id = 1L;
        Product product = new Product();
        String username = "testUser";
        int quantity = 2;
        LocalDateTime createdTime = LocalDateTime.now();

        // Act
        order.setId(id);
        order.setProduct(product);
        order.setUsername(username);
        order.setQuantity(quantity);
        order.setCreatedTime(createdTime);

        // Assert
        assertEquals(id, order.getId());
        assertEquals(product, order.getProduct());
        assertEquals(username, order.getUsername());
        assertEquals(quantity, order.getQuantity());
        assertEquals(createdTime, order.getCreatedTime());
    }
}
