package com.task2.blogapp.controller;

import com.task2.blogapp.entities.User;
import com.task2.blogapp.payload.ApiResponse;
import com.task2.blogapp.payload.UserDto;
import com.task2.blogapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/addresses/{addressId}")
    public ResponseEntity<User> createUser(@RequestBody User user, @PathVariable Integer addressId) {
        User createUser = this.userService.createUser(user, addressId);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId) {
        UserDto updateUserDto = this.userService.updateUser(userId);
        return ResponseEntity.ok(updateUserDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity(new ApiResponse("Deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserDto>> getSingleUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(Collections.singletonList(this.userService.getUserById(userId)));


    }

    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<List<UserDto>> getUsersByAddress(@PathVariable Integer addressId) {
        List<UserDto> users = this.userService.getUsersByAddress(addressId);
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }


}

