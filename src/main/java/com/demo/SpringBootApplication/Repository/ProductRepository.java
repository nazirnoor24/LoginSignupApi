package com.demo.SpringBootApplication.Repository;

import com.demo.SpringBootApplication.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
