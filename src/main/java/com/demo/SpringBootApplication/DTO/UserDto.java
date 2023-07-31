package com.demo.SpringBootApplication.DTO;


import com.demo.SpringBootApplication.Entity.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private int id;
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 4,message = "firstname must be greter then 4 character")
    private String firstName;
    private String lastName;
    @Email(message = "Email is not valid")
    @NotEmpty(message = "Email should not be null")
    private String email;
    @NotNull
    private int age;
    @NotNull
    private String gender;
    private List<Product> productList;





}
