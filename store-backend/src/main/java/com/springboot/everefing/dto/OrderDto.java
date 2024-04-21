package com.springboot.everefing.dto;

import com.springboot.everefing.entity.Product;
import com.springboot.everefing.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Product product;
    private String username;
    private int quantity;
    private LocalDateTime localDateTime;
}
