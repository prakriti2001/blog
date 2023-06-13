package com.task2.blogapp.servicesImpl;

import com.task2.blogapp.entities.Address;
import com.task2.blogapp.entities.User;
import com.task2.blogapp.exception.ResourceNotFoundException;
import com.task2.blogapp.mappers.UserMapper;
import com.task2.blogapp.payload.UserDto;
import com.task2.blogapp.repositories.AddressRepo;
import com.task2.blogapp.repositories.UserRepo;
import com.task2.blogapp.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final AddressRepo addressRepo;

    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper, AddressRepo addressRepo) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.addressRepo = addressRepo;
    }

    @Override
    public UserDto createUser(User user, Integer addressId) {
        Address address = addressRepo.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "Id", addressId));
        user.setAddress(address);
        User createdUser = userRepo.save(user);
        return this.userMapper.mapUserToUserDto(createdUser);
    }

    @Override
    public UserDto updateUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setUserName(user.getUserName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setAbout(user.getAbout());
        User updatedUser = this.userRepo.save(user);
        return userMapper.mapUserToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return userMapper.mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return userMapper.userDTOList(users);

    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        userRepo.delete(user);
    }

    @Override
    public List<UserDto> getUsersByAddress(Integer addressId) {
        Address address = addressRepo.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "Id", addressId));
        List<User> users = userRepo.findByAddress(address);
        return users.stream()
                .map(userMapper::mapUserToUserDto)
                .collect(Collectors.toList());
    }


}
