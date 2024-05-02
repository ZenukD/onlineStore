package com.springboot.everefing.dto;

import com.springboot.everefing.dto.OrderDto;
import com.springboot.everefing.entity.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderDtoTest {

    @Test
    void testConstructor() {
        // Arrange
        Long id = 1L;
        Product product = new Product();
        String username = "testUser";
        int quantity = 2;
        LocalDateTime localDateTime = LocalDateTime.now();

        // Act
        OrderDto orderDto = new OrderDto(id, product, username, quantity, localDateTime);

        // Assert
        assertNotNull(orderDto);
        assertEquals(id, orderDto.getId());
        assertEquals(product, orderDto.getProduct());
        assertEquals(username, orderDto.getUsername());
        assertEquals(quantity, orderDto.getQuantity());
        assertEquals(localDateTime, orderDto.getLocalDateTime());
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        OrderDto orderDto = new OrderDto();
        Long id = 1L;
        Product product = new Product();
        String username = "testUser";
        int quantity = 2;
        LocalDateTime localDateTime = LocalDateTime.now();

        // Act
        orderDto.setId(id);
        orderDto.setProduct(product);
        orderDto.setUsername(username);
        orderDto.setQuantity(quantity);
        orderDto.setLocalDateTime(localDateTime);

        // Assert
        assertEquals(id, orderDto.getId());
        assertEquals(product, orderDto.getProduct());
        assertEquals(username, orderDto.getUsername());
        assertEquals(quantity, orderDto.getQuantity());
        assertEquals(localDateTime, orderDto.getLocalDateTime());
    }
}
