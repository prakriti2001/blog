package com.task2.blogapp.repositories;

import com.task2.blogapp.entities.Address;
import com.task2.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    List<User> findByAddress(Address address);


}
