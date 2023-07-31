package com.demo.SpringBootApplication.Services;

import com.demo.SpringBootApplication.DTO.OrderDto;
import com.demo.SpringBootApplication.Entity.Order;

import java.util.List;

public interface OrderService {
    public OrderDto getOrderById(int id);
    public List<OrderDto> getAllOrder();

    OrderDto addOrder(OrderDto orderDto);

    void deleteOrder(int id);

    OrderDto updateOrder(int id , OrderDto orderDto);
}
