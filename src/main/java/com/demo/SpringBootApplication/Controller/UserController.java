package com.demo.SpringBootApplication.Controller;


import com.demo.SpringBootApplication.DTO.UserDto;
import com.demo.SpringBootApplication.Entity.User;
import com.demo.SpringBootApplication.DTO.ApiResponse;
import com.demo.SpringBootApplication.Services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> userDto = this.userServices.getAllUSer();
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        this.userServices.deleteUser(id);
        return new ResponseEntity<ApiResponse>(
                new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> getUserById(@PathVariable int id) {
        Optional<UserDto> user = this.userServices.getUserById(id);
        return ResponseEntity.ok(user);

    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto user) {
        UserDto user1 = this.userServices.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserDto user) {
        this.userServices.updateUser(id, user);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Update Successfully", true), HttpStatus.OK);


    }
}
