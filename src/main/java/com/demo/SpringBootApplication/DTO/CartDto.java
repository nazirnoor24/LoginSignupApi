package com.demo.SpringBootApplication.DTO;

import com.demo.SpringBootApplication.Entity.Product;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;
    @NotEmpty(message = "UserId must not be empty")
    private int userId;
    private List<Product> productsList;
}
