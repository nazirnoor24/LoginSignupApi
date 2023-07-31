package com.demo.SpringBootApplication.Implements;

import com.demo.SpringBootApplication.DTO.OrderDto;
import com.demo.SpringBootApplication.Entity.Order;
import com.demo.SpringBootApplication.Exception.ResourceNotFoundException;
import com.demo.SpringBootApplication.Repository.OrderRepository;
import com.demo.SpringBootApplication.Services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public OrderDto getOrderById(int id) {
        Order order = this.orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order","ID",id));
        return orderToDto(order);
    }

    @Override
    public List<OrderDto> getAllOrder() {
        List<OrderDto> orderList = this.orderRepository.findAll()
                .stream().map(order -> this.orderToDto(order)).collect(Collectors.toList());
        return orderList;
    }

    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        Order order = this.dtoToOrder(orderDto);
        Order saved = this.orderRepository.save(order);
        return orderToDto(saved);
    }

    @Override
    public void deleteOrder(int id) {
        Order order = this.orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order","ID",id));
        this.orderRepository.delete(order);
    }

    @Override
    public OrderDto updateOrder(int id, OrderDto orderDto) {
        Order order = this.dtoToOrder(orderDto);
        order.setOrderDate(order.getOrderDate());
        order.setTotalAmount(order.getTotalAmount());
        Order update = this.orderRepository.save(order);
        return this.orderToDto(order);
    }

    public OrderDto orderToDto(Order order){
        OrderDto orderDto = this.modelMapper.map(order,OrderDto.class);
        return orderDto;
    }
    public Order dtoToOrder(OrderDto orderDto){
        Order order = this.modelMapper.map(orderDto,Order.class);
        return order;
    }

}
