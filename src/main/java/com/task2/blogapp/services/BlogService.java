package com.task2.blogapp.services;

import com.task2.blogapp.entities.Blog;
import com.task2.blogapp.payload.BlogDto;

import java.util.List;

public interface BlogService {
    Blog createBlog(Blog blog, Integer userId);

    BlogDto updateBlog(Integer blogId);

    void deleteBlog(Integer blogId);

    List<BlogDto> getAllBlog();

    BlogDto getBlogById(Integer blogId);

    List<BlogDto> getBlogsByUser(Integer userId);

}
