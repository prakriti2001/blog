package com.task2.blogapp.controller;

import com.task2.blogapp.entities.Blog;
import com.task2.blogapp.payload.ApiResponse;
import com.task2.blogapp.payload.BlogDto;
import com.task2.blogapp.services.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Blog")
@RequestMapping("/api/")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }


    @Operation(
            description = "Post endpoint for Blogs",
            summary = "This is a summary for blog post endpoint",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Success",
                            responseCode = "200"

                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"

                    )

            }
    )

    @PostMapping("/users/{userId}/blogs")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog, @PathVariable Integer userId) {
        Blog createBlog = this.blogService.createBlog(blog, userId);
        return new ResponseEntity<Blog>(createBlog, HttpStatus.CREATED);

    }

    @Operation(
            description = "Get endpoint for Blogs",
            summary = "This is a summary for blog get endpoint",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Success",
                            responseCode = "200"

                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"

                    )

            }
    )
    @GetMapping("/users/{userId}/blogs")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<BlogDto>> getBlogsByUser(@PathVariable Integer userId) {
        List<BlogDto> blogs = this.blogService.getBlogsByUser(userId);
        return new ResponseEntity<List<BlogDto>>(blogs, HttpStatus.OK);

    }

    @Operation(
            description = "Get endpoint for Blogs",
            summary = "This is a summary for blog get endpoint",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Success",
                            responseCode = "200"

                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"

                    )

            }
    )

    @GetMapping("/blogs")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<BlogDto>> getAllBlog() {
        List<BlogDto> allBlogs = this.blogService.getAllBlog();
        return new ResponseEntity<List<BlogDto>>(allBlogs, HttpStatus.OK);

    }

    @Operation(
            description = "Get endpoint for Blogs",
            summary = "This is a summary for blog get endpoint",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Success",
                            responseCode = "200"

                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"

                    )

            }
    )
    @GetMapping("/blogs/{blogId}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable Integer blogId) {
        BlogDto blogDto = this.blogService.getBlogById(blogId);
        return new ResponseEntity<BlogDto>(blogDto, HttpStatus.OK);
    }

    @Operation(
            description = "Delete endpoint for Blogs",
            summary = "This is a summary for blog delete endpoint",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Success",
                            responseCode = "200"

                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"

                    )

            }
    )
    @DeleteMapping("/blogs/{blogId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ApiResponse deleteBlog(@PathVariable Integer blogId) {
        this.blogService.deleteBlog(blogId);
        return new ApiResponse("Blog is deleted successfully", true);

    }

    @Operation(
            description = "Update endpoint for Blogs",
            summary = "This is a summary for blog update endpoint",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Success",
                            responseCode = "200"

                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"

                    )

            }
    )
    @PutMapping("/blogs/{blogId}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<BlogDto> updateBlog(@PathVariable Integer blogId) {
        BlogDto updateBlog = this.blogService.updateBlog(blogId);
        return new ResponseEntity<BlogDto>(updateBlog, HttpStatus.OK);
    }


}
