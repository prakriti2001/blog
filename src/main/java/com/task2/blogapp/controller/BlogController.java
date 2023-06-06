package com.task2.blogapp.controller;

import com.task2.blogapp.entities.Blog;
import com.task2.blogapp.payload.ApiResponse;
import com.task2.blogapp.payload.BlogDto;
import com.task2.blogapp.services.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }


    @PostMapping("/users/{userId}/blogs")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog, @PathVariable Integer userId) {
        Blog createBlog = this.blogService.createBlog(blog, userId);
        return new ResponseEntity<Blog>(createBlog, HttpStatus.CREATED);

    }

    @GetMapping("/users/{userId}/blogs")
    public ResponseEntity<List<BlogDto>> getBlogsByUser(@PathVariable Integer userId) {
        List<BlogDto> blogs = this.blogService.getBlogsByUser(userId);
        return new ResponseEntity<List<BlogDto>>(blogs, HttpStatus.OK);

    }

    @GetMapping("/blogs")
    public ResponseEntity<List<BlogDto>> getAllBlog() {
        List<BlogDto> allBlogs = this.blogService.getAllBlog();
        return new ResponseEntity<List<BlogDto>>(allBlogs, HttpStatus.OK);

    }

    @GetMapping("/blogs/{blogId}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable Integer blogId) {
        BlogDto blogDto = this.blogService.getBlogById(blogId);
        return new ResponseEntity<BlogDto>(blogDto, HttpStatus.OK);
    }

    @DeleteMapping("/blogs/{blogId}")
    public ApiResponse deleteBlog(@PathVariable Integer blogId) {
        this.blogService.deleteBlog(blogId);
        return new ApiResponse("Blog is deleted successfully", true);

    }

    @PutMapping("/blogs/{blogId}")
    public ResponseEntity<BlogDto> updateBlog(@PathVariable Integer blogId) {
        BlogDto updateBlog = this.blogService.updateBlog(blogId);
        return new ResponseEntity<BlogDto>(updateBlog, HttpStatus.OK);
    }


}
