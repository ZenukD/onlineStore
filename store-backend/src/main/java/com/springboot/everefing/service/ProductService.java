package com.springboot.everefing.service;

import com.springboot.everefing.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    ProductDto addProduct(ProductDto productDto);
    ProductDto getProduct(Long productId);
    List<ProductDto> getAllProducts();
    ProductDto updateProduct(Long productId, ProductDto productDto);
    void deleteProduct(Long productId);
}
