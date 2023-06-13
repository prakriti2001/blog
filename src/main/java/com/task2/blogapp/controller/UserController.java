package com.task2.blogapp.controller;

import com.task2.blogapp.entities.User;
import com.task2.blogapp.payload.ApiResponse;
import com.task2.blogapp.payload.AuthRequest;
import com.task2.blogapp.payload.UserDto;
import com.task2.blogapp.services.JwtService;
import com.task2.blogapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserController(PasswordEncoder passwordEncoder, UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("addresses/{addressId}")
    public ResponseEntity<UserDto> createUser(@RequestBody User user, @PathVariable Integer addressId) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserDto userDto = this.userService.createUser(user, addressId);
        return ResponseEntity.ok(userDto);
    }


    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId) {
        UserDto updateUserDto = this.userService.updateUser(userId);
        return ResponseEntity.ok(updateUserDto);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("Deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getSingleUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(Collections.singletonList(this.userService.getUserById(userId)));


    }

    @GetMapping("/users/addresses/{addressId}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getUsersByAddress(@PathVariable Integer addressId) {
        List<UserDto> users = this.userService.getUsersByAddress(addressId);
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }


    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid User Request!");
        }

    }

}





