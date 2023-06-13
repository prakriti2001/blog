package com.task2.blogapp.repositories;

import com.task2.blogapp.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Roles, Integer> {
}
