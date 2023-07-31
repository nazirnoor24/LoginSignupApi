package com.demo.SpringBootApplication.Controller;

import com.demo.SpringBootApplication.DTO.ApiResponse;
import com.demo.SpringBootApplication.DTO.CartDto;
import com.demo.SpringBootApplication.DTO.OrderDto;
import com.demo.SpringBootApplication.DTO.ProductDto;
import com.demo.SpringBootApplication.Entity.Cart;
import com.demo.SpringBootApplication.Entity.Order;
import com.demo.SpringBootApplication.Entity.Product;
import com.demo.SpringBootApplication.Services.CartService;
import com.demo.SpringBootApplication.Services.OrderService;
import org.hibernate.validator.cfg.defs.CreditCardNumberDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private OrderService orderService;


    @Autowired
    private CartService cartService;
    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCart(){
        List<CartDto> cartDtos =  this.cartService.getAllCart();
        return ResponseEntity.ok(cartDtos);
    }


    @PostMapping
    public ResponseEntity<CartDto> postCart(@RequestBody CartDto cart){
        CartDto cartDto = this.cartService.addCart(cart);
        OrderDto order = new OrderDto();
        List<Product> list = cart.getProductsList();
        long price = 0;
        for (int i = 0 ; i < list.size();i++){
            System.out.println(list.get(i).getProductPrice());
            price = price + list.get(i).getProductPrice();
            list.get(i).setUserId((long) cartDto.getUserId());
        }
        LocalDate today = LocalDate.now();
        order.setUserId((long) cartDto.getUserId());
        order.setTotalAmount(price);
        order.setOrderDate(today.toString());
        orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getSingleCart(@PathVariable int id){
        CartDto cartDto = this.cartService.getCartById(id);
        return ResponseEntity.ok(cartDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable int id){
        this.cartService.deleteCart(id);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Cart Deleted Successfully",true),HttpStatus.OK);
    }


    @PutMapping({"/product/{id}"})
    public ResponseEntity<?> updateProduct(@PathVariable int id , @RequestBody CartDto cartDto){
        this.cartService.updateCart(id,cartDto);
        return new ResponseEntity<ApiResponse>(new ApiResponse("product Update successfully",true),HttpStatus.OK);
    }
}
