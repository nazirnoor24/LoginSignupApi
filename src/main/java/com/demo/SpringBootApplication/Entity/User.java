package com.demo.SpringBootApplication.Entity;


import com.demo.SpringBootApplication.DTO.OrderDto;
import com.demo.SpringBootApplication.DTO.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String gender;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Product> productList = new ArrayList<>();
}
