package com.demo.SpringBootApplication.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CartTable")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int userId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> productsList=new ArrayList<>();
}
