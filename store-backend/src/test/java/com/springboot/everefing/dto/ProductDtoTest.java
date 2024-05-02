package com.springboot.everefing.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductDtoTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        ProductDto productDto = new ProductDto();

        // Set values
        productDto.setId(1L);
        productDto.setName("Test Product");
        productDto.setDescription("Test Description");
        productDto.setPrice(BigDecimal.valueOf(99.99));
        productDto.setImage(new byte[] {0, 1, 2, 3});

        // Assert
        assertEquals(1L, productDto.getId());
        assertEquals("Test Product", productDto.getName());
        assertEquals("Test Description", productDto.getDescription());
        assertEquals(BigDecimal.valueOf(99.99), productDto.getPrice());
        assertArrayEquals(new byte[] {0, 1, 2, 3}, productDto.getImage());
    }

    @Test
    void testBuilder() {
        // Arrange
        byte[] sampleImage = new byte[] {0, 1, 2, 3};

        // Act
        ProductDto productDto = ProductDto.builder()
                .id(1L)
                .name("Test Product")
                .description("Test Description")
                .price(BigDecimal.valueOf(99.99))
                .image(sampleImage)
                .build();

        // Assert
        assertNotNull(productDto);
        assertEquals(1L, productDto.getId());
        assertEquals("Test Product", productDto.getName());
        assertEquals("Test Description", productDto.getDescription());
        assertEquals(BigDecimal.valueOf(99.99), productDto.getPrice());
        assertArrayEquals(sampleImage, productDto.getImage());
    }
}

