package com.demo.SpringBootApplication.Repository;

import com.demo.SpringBootApplication.Entity.Order;
import jakarta.persistence.criteria.CriteriaBuilder;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order,Integer> {
}
