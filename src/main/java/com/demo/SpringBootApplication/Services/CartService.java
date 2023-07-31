package com.demo.SpringBootApplication.Services;

import com.demo.SpringBootApplication.DTO.CartDto;
import org.hibernate.validator.cfg.defs.CreditCardNumberDef;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;



public interface CartService {
    CartDto addCart(CartDto cartDto);
    CartDto updateCart(int id ,CartDto cartDto);
    void deleteCart(int id);
    List<CartDto> getAllCart();

    CartDto getCartById(int id);
}
