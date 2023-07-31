package com.demo.SpringBootApplication.Controller;


import com.demo.SpringBootApplication.DTO.ProductDto;
import com.demo.SpringBootApplication.Entity.Product;
import com.demo.SpringBootApplication.DTO.ApiResponse;
import com.demo.SpringBootApplication.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
        ProductDto productDto = this.productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductDto>> getSingleProduct(@PathVariable int id){
        Optional<ProductDto> product = this.productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        List<ProductDto> products = this.productService.getAllProduct();
        return ResponseEntity.ok(products);
    }
    private HttpStatus httpStatus;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        this.productService.deleteProduct(id);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Product Deleted Successfully",true),HttpStatus.OK);
    }

    @PutMapping({"/product/{id}"})
    public ResponseEntity<?> updateProduct(@PathVariable int id , @RequestBody ProductDto product){
        this.productService.updateProduct(id,product);
        return new ResponseEntity<ApiResponse>(new ApiResponse("product Update successfully",true),HttpStatus.OK);
    }




}
