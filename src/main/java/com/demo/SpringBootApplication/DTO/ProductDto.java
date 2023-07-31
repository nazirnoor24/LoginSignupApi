package com.demo.SpringBootApplication.DTO;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private int productId;
    @NotEmpty(message = "productName must not be empty")
    private String productName;
    @NotEmpty(message = "product price must not be empty")
    private Long productPrice;
}
