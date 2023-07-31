package com.demo.SpringBootApplication.Implements;

import com.demo.SpringBootApplication.DTO.ProductDto;
import com.demo.SpringBootApplication.DTO.UserDto;
import com.demo.SpringBootApplication.Entity.Product;
import com.demo.SpringBootApplication.Entity.User;
import com.demo.SpringBootApplication.Exception.ResourceNotFoundException;
import com.demo.SpringBootApplication.Repository.ProductRepository;
import com.demo.SpringBootApplication.Repository.UserRepository;
import com.demo.SpringBootApplication.Services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ProductDto createProduct(ProductDto dtoproduct) {
        Product product = this.dtoToProduct(dtoproduct);
        Product saved = this.productRepository.save(product);
        return this.productToDto(saved);
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<ProductDto> products = this.productRepository.findAll().stream().map(product -> this.productToDto(product)).collect(Collectors.toList());
        return products;
    }

    @Override
    public Optional<ProductDto> getProductById(int id) {
        Product product = this.productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product","Id",id));
        return Optional.ofNullable(this.productToDto(product));

    }

    @Override
    public void deleteProduct(int id) {
        Product product = this.productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product" , "Id" ,id));
        this.productRepository.delete(product);

    }

    @Override
    public ProductDto updateProduct(int id, ProductDto product) {
        Product product1 = this.productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product" , "ID",id));
        product1.setProductPrice(product.getProductPrice());
        product1.setProductName(product.getProductName());
        Product product2 = this.productRepository.save(product1);
        return productToDto(product2);
    }


    public ProductDto productToDto(Product product){
        ProductDto productDto = this.modelMapper.map(product,ProductDto.class);
        return productDto;
    }
    public Product dtoToProduct(ProductDto productDto){
        Product product = this.modelMapper.map(productDto,Product.class);
        return product;
    }
}
