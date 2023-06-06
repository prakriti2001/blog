package com.task2.blogapp.repositories;

import com.task2.blogapp.entities.Blog;
import com.task2.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepo extends JpaRepository<Blog, Integer> {
    List<Blog> findByUser(User user);
}
