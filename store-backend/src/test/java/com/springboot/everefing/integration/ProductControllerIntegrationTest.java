package com.springboot.everefing.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.everefing.controller.ProductController;
import com.springboot.everefing.dto.ProductDto;
import com.springboot.everefing.entity.Product;
import com.springboot.everefing.repository.ProductRepository;
import com.springboot.everefing.service.ProductService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddProduct() throws Exception {
        // Given
        ProductDto productDto = ProductDto.builder()
                .name("product")
                .description("product description")
                .price(BigDecimal.valueOf(10))
                .build();
        // When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",
                        CoreMatchers.is(productDto.getName())))
                .andExpect(jsonPath("$.description",
                        CoreMatchers.is(productDto.getDescription())))
                .andExpect(jsonPath("$.price",
                        CoreMatchers.is(productDto.getPrice().intValue())));
    }

    @Test
    public void testGetProduct() throws Exception {
        // given - precondition or setup
        long productId = 1L;
        Product productDto = Product.builder()
                .name("product")
                .description("product description")
                .price(BigDecimal.valueOf(10))
                .build();
        productRepository.save(productDto);
        // when - action or behavior that we want to test
        ResultActions response = mockMvc.perform(get("/api/products/{id}", productDto.getId()));
        // then - verify the result
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",
                        CoreMatchers.is(productDto.getName())))
                .andExpect(jsonPath("$.description",
                        CoreMatchers.is(productDto.getDescription())))
                .andExpect(jsonPath("$.price",
                        CoreMatchers.is(productDto.getPrice().doubleValue())));
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // given - precondition or setup
        List<Product> productDtoList = new ArrayList<>();
        productDtoList.add(Product.builder()
                .name("product")
                .description("product description")
                .price(BigDecimal.valueOf(10))
                .build());
        productDtoList.add(Product.builder()
                .name("product1")
                .description("product description1")
                .price(BigDecimal.valueOf(101))
                .build());
        productRepository.saveAll(productDtoList);
        // when - action or behavior that we want to test
        ResultActions response = mockMvc.perform(get("/api/products"));
        // then - verify the result
        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateProduct() throws Exception {
        // given - precondition or setup
        long productId = 1L;
        Product savedProduct = Product.builder()
                .name("product")
                .description("product description")
                .price(BigDecimal.valueOf(10))
                .build();
        productRepository.save(savedProduct);
        Product updatedProduct = Product.builder()
                .name("product1")
                .description("product description1")
                .price(BigDecimal.valueOf(101))
                .build();
        ResultActions response = mockMvc.perform(put("/api/products/{id}", savedProduct.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProduct)));
        // then - verify the result
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",
                        CoreMatchers.is(updatedProduct.getName())))
                .andExpect(jsonPath("$.description",
                        CoreMatchers.is(updatedProduct.getDescription())))
                .andExpect(jsonPath("$.price",
                        CoreMatchers.is(updatedProduct.getPrice().intValue())));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        // given - precondition or setup
        Product savedProduct = Product.builder()
                .name("product")
                .description("product description")
                .price(BigDecimal.valueOf(10))
                .build();
        productRepository.save(savedProduct);
        // when - action or behavior that we want to test
        ResultActions response = mockMvc.perform(delete("/api/products/{id}", savedProduct.getId()));
        // then - verify the result
        response.andDo(print())
                .andExpect(status().isOk());
    }
}
