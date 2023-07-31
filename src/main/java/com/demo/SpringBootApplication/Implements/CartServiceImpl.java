package com.demo.SpringBootApplication.Implements;

import com.demo.SpringBootApplication.DTO.CartDto;
import com.demo.SpringBootApplication.Entity.Cart;
import com.demo.SpringBootApplication.Entity.User;
import com.demo.SpringBootApplication.Exception.ResourceNotFoundException;
import com.demo.SpringBootApplication.Repository.CartRepository;
import com.demo.SpringBootApplication.Repository.UserRepository;
import com.demo.SpringBootApplication.Services.CartService;
import com.demo.SpringBootApplication.Services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CartDto addCart(CartDto cartDto) {

        this.userRepository.findById(cartDto.getUserId()).orElseThrow(()->new ResourceNotFoundException("User","Id",cartDto.getUserId()));
            Cart cart = this.dtoToCart(cartDto);
            Cart cart1 = this.cartRepository.save(cart);
            return this.cartToDto(cart1);

    }

    @Override
    public CartDto updateCart(int id ,CartDto cartDto) {
        Cart cart = this.dtoToCart(cartDto);
        cart.setUserId(cartDto.getUserId());
        cart.setProductsList(cartDto.getProductsList());
        Cart update = this.cartRepository.save(cart);
        return cartToDto(cart);
    }

    @Override
    public void deleteCart(int id) {

        Cart cart = this.cartRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cart","Id",id));
    }

    @Override
    public List<CartDto> getAllCart() {
        List<CartDto> cart = this.cartRepository.findAll().stream().map(cart1 -> this.cartToDto(cart1)).collect(Collectors.toList());
        return cart;
    }

    @Override
    public CartDto getCartById(int id) {
        Cart cart = this.cartRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cart","ID",id));
        return cartToDto(cart);
    }


    public CartDto cartToDto(Cart cart){
        CartDto cartDto = this.modelMapper.map(cart,CartDto.class);
        return cartDto;
    }

    public Cart dtoToCart(CartDto cartDto){
        Cart cart = this.modelMapper.map(cartDto,Cart.class);
        return cart;
    }
}
