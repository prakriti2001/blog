package com.task2.blogapp.repositories;

import com.task2.blogapp.entities.Blog;
import com.task2.blogapp.entities.Comment;
import com.task2.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findByBlog(Blog blog);

    List<Comment> findByUser(User user);
}
