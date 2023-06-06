package com.task2.blogapp.repositories;

import com.task2.blogapp.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Integer> {
}
