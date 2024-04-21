package com.springboot.everefing.service.impl;

import com.springboot.everefing.dto.ProductDto;
import com.springboot.everefing.entity.Product;
import com.springboot.everefing.exception.ResourceNotFoundException;
import com.springboot.everefing.repository.ProductRepository;
import com.springboot.everefing.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;



    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);

        Product saveProduct = productRepository.save(product);

        return modelMapper.map(saveProduct, ProductDto.class);
    }

    @Override
    public ProductDto getProduct(Long productId) {

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Not found product with such id: " + productId)
        );

        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map((product) -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Not found product with such id: " + productId)
        );
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());

        Product updateProduct = productRepository.save(product);

        return modelMapper.map(updateProduct, ProductDto.class);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Not found product with such id: " + productId)
        );
        productRepository.deleteById(productId);
    }

}
