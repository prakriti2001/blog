package com.task2.blogapp.services;

import com.task2.blogapp.entities.User;
import com.task2.blogapp.payload.UserDto;

import java.util.List;


public interface UserService {


    UserDto updateUser(Integer userId);

    UserDto getUserById(Integer userId);

    User createUser(User user, Integer addressId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);

    List<UserDto> getUsersByAddress(Integer addressId);


}
