package com.demo.SpringBootApplication.Implements;

import com.demo.SpringBootApplication.DTO.UserDto;
import com.demo.SpringBootApplication.Entity.User;
import com.demo.SpringBootApplication.Exception.ResourceNotFoundException;
import com.demo.SpringBootApplication.Repository.UserRepository;
import com.demo.SpringBootApplication.Services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServuicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User saved  = this.userRepository.save(user);
        return this.userToDto(saved);
    }
    @Override
    public void deleteUser(int id) {
        User user = this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","Id",id));
        this.userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(int id, UserDto userDto) {
        User user = this.userRepository.findById(id)
                .orElseThrow( ()->   new ResourceNotFoundException("User","Id",id));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setGender(userDto.getGender());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
        User updateUser = this.userRepository.save(user);
        return userToDto(updateUser);
    }

    @Override
    public Optional<UserDto> getUserById(int id) {
        User user = this.userRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
        return Optional.ofNullable(this.userToDto(user));
    }

    @Override
    public List<UserDto> getAllUSer() {
        List<User> users = this.userRepository.findAll();
         List<UserDto> userDtos = users.
                 stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
         return userDtos;
    }


    public User dtoToUser (UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);
        return user;
    }
    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        return userDto;
    }



}
//logging
//sl4j
//pagination
//model mapper