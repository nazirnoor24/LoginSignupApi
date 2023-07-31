package com.demo.SpringBootApplication.Services;

import com.demo.SpringBootApplication.DTO.ProductDto;
import com.demo.SpringBootApplication.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
     ProductDto createProduct(ProductDto productDto);
     List<ProductDto> getAllProduct();
     Optional<ProductDto> getProductById(int id);
     void deleteProduct(int id);
     ProductDto updateProduct(int id , ProductDto product);




}
