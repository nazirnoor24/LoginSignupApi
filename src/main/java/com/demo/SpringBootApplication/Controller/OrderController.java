package com.demo.SpringBootApplication.Controller;


import com.demo.SpringBootApplication.DTO.ApiResponse;
import com.demo.SpringBootApplication.DTO.OrderDto;
import com.demo.SpringBootApplication.DTO.UserDto;
import com.demo.SpringBootApplication.Entity.Order;
import com.demo.SpringBootApplication.Entity.Product;
import com.demo.SpringBootApplication.Entity.User;
import com.demo.SpringBootApplication.Repository.OrderRepository;
import com.demo.SpringBootApplication.Repository.ProductRepository;
import com.demo.SpringBootApplication.Repository.UserRepository;
import com.demo.SpringBootApplication.Services.OrderService;
import com.demo.SpringBootApplication.Services.ProductService;
import com.demo.SpringBootApplication.Services.UserServices;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {



    @Autowired
    private OrderService orderService;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto){
        OrderDto orderDto1 =  this.orderService.addOrder(orderDto);
        return new ResponseEntity<>(orderDto1,HttpStatus.CREATED);
    }

    @PostMapping("/placeorder")
    public User addUser(@RequestBody User user){
        OrderDto order = new OrderDto();
        User userDto =  this.userRepository.save(user);
        List<Product> list = userDto.getProductList();
        long price = 0;
        for (int i = 0 ; i < list.size();i++){
            System.out.println(list.get(i).getProductPrice());
            price = price + list.get(i).getProductPrice();
            list.get(i).setUserId(user.getId());
        }


        LocalDate today = LocalDate.now();
        order.setUserId(user.getId());
        order.setTotalAmount(price);
        order.setOrderDate(today.toString());
        orderService.addOrder(order);

        return userDto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable int id){
        OrderDto orderDto = this.orderService.getOrderById(id);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        List<OrderDto> orderDtos = this.orderService.getAllOrder();
        return ResponseEntity.ok(orderDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable int id ,@RequestBody OrderDto orderDto){
        this.orderService.updateOrder(id,orderDto);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Order Update Successfull",true),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable int id){
        this.orderService.deleteOrder(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Order Deleted Successfully",true),HttpStatus.OK);
    }
    //javers

}
