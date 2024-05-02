package com.springboot.everefing.service.impl;

import com.springboot.everefing.dto.ProductDto;
import com.springboot.everefing.entity.Product;
import com.springboot.everefing.repository.ProductRepository;
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

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testAddProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        Product product = new Product();

        when(modelMapper.map(productDto, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);

        ProductDto savedProductDto = productService.addProduct(productDto);

        assertNotNull(savedProductDto);
        assertEquals(productDto.getId(), savedProductDto.getId());
        verify(productRepository, times(1)).save(product);
    }


    @Test
    void testGetProduct() {
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(modelMapper.map(product, ProductDto.class)).thenReturn(new ProductDto());

        ProductDto retrievedProductDto = productService.getProduct(productId);

        assertNotNull(retrievedProductDto);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productList);
        when(modelMapper.map(any(Product.class), eq(ProductDto.class))).thenReturn(new ProductDto());

        List<ProductDto> productDtoList = productService.getAllProducts();

        assertNotNull(productDtoList);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        ProductDto productDto = new ProductDto();
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);

        ProductDto updatedProductDto = productService.updateProduct(productId, productDto);

        assertNotNull(updatedProductDto);
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }
}
