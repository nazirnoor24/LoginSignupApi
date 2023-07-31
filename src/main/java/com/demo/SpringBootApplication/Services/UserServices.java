package com.demo.SpringBootApplication.Services;

import com.demo.SpringBootApplication.DTO.UserDto;
import com.demo.SpringBootApplication.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserServices {

    UserDto createUser(UserDto userDto);

    public void deleteUser(int id);
    public UserDto updateUser(int id , UserDto user);
    public Optional<UserDto> getUserById(int id);
    public List<UserDto> getAllUSer();

}
