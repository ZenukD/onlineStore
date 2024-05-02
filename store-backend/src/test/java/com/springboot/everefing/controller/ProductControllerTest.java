package com.springboot.everefing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.everefing.dto.ProductDto;
import com.springboot.everefing.entity.Product;
import com.springboot.everefing.service.AuthService;
import com.springboot.everefing.service.OrderService;
import com.springboot.everefing.service.ProductService;
import com.springboot.everefing.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
//@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AuthService authService;
    @MockBean
    private OrderService orderService;
    @MockBean
    private UserService userService;
    @MockBean
    private ProductService productService;

    @Test
    public void testCreatedProduct() throws Exception {
        // given - precondition or setup
        ProductDto productDto = ProductDto.builder()
                .name("product")
                .description("product description")
                .price(BigDecimal.valueOf(10))
                .build();

        BDDMockito.given(productService.addProduct(ArgumentMatchers.any(ProductDto.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // when - action or behavior that we want to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));


        // then - verify the result
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
        ProductDto productDto = ProductDto.builder()
                .name("product")
                .description("product description")
                .price(BigDecimal.valueOf(10))
                .build();

        BDDMockito.given(productService.getProduct(productId))
                .willReturn(productDto);

        // when - action or behavior that we want to test

        ResultActions response = mockMvc.perform(get("/api/products/{id}", productId));
        // then - verify the result
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",
                        CoreMatchers.is(productDto.getName())))
                .andExpect(jsonPath("$.description",
                        CoreMatchers.is(productDto.getDescription())))
                .andExpect(jsonPath("$.price",
                        CoreMatchers.is(productDto.getPrice().intValue())));
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // given - precondition or setup
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(ProductDto.builder()
                .name("product")
                .description("product description")
                .price(BigDecimal.valueOf(10))
                .build());
        productDtoList.add(ProductDto.builder()
                .name("product1")
                .description("product description1")
                .price(BigDecimal.valueOf(101))
                .build());
        BDDMockito.given(productService.getAllProducts()).willReturn(productDtoList);

        // when - action or behavior that we want to test
        ResultActions response = mockMvc.perform(get("/api/products"));
        // then - verify the result
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",
                        CoreMatchers.is(productDtoList.size())));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        // given - precondition or setup
        long productId = 1L;
        BDDMockito.willDoNothing().given(productService).deleteProduct(productId);
        // when - action or behavior that we want to test
        ResultActions response = mockMvc.perform(delete("/api/products/{id}", productId));
        // then - verify the result
        response.andDo(print())
                .andExpect(status().isOk());
    }
}
