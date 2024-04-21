package com.springboot.everefing.controller;

import com.springboot.everefing.dto.ProductDto;
import com.springboot.everefing.service.ProductService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProduct() {
        ProductDto productDto = new ProductDto();
        when(productService.addProduct(any(ProductDto.class))).thenReturn(productDto);

        ResponseEntity<ProductDto> response = productController.addProduct(productDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(productDto, response.getBody());
    }

    @Test
    public void testGetProduct() {
        Long productId = 1L;
        ProductDto productDto = new ProductDto();
        when(productService.getProduct(productId)).thenReturn(productDto);

        ResponseEntity<ProductDto> response = productController.getProduct(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDto, response.getBody());
    }

    @Test
    public void testGetAllProducts() {
        ProductDto productDto1 = new ProductDto();
        ProductDto productDto2 = new ProductDto();
        List<ProductDto> productDtoList = Arrays.asList(productDto1, productDto2);
        when(productService.getAllProducts()).thenReturn(productDtoList);

        ResponseEntity<List<ProductDto>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDtoList, response.getBody());
    }

    @Test
    public void testUpdateProduct() {
        Long productId = 1L;
        ProductDto productDto = new ProductDto(); // Create a ProductDto object
        when(productService.updateProduct(eq(productId), any(ProductDto.class))).thenReturn(productDto);

        ResponseEntity<ProductDto> response = productController.updateProduct(productId, productDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDto, response.getBody());
    }

    @Test
    public void testDeleteProduct() {
        Long productId = 1L;
        doNothing().when(productService).deleteProduct(productId);

        ResponseEntity<String> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product deleted successfully!", response.getBody());
    }
}
