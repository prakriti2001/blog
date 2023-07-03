package com.task2.blogapp.controller;

import com.task2.blogapp.entities.Comment;
import com.task2.blogapp.payload.ApiResponse;
import com.task2.blogapp.payload.CommentDto;
import com.task2.blogapp.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Tag(name = "Comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(
            description = "Post endpoint for Comments",
            summary = "This is a summary for comment post endpoint",
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

    @PostMapping("/users/{userId}/blogs/{blogId}/comments")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @PathVariable Integer blogId, @PathVariable Integer userId) {
        Comment createComment = this.commentService.createComment(comment, blogId, userId);
        return new ResponseEntity<Comment>(createComment, HttpStatus.CREATED);
    }

    @Operation(
            description = "Delete endpoint for Comments",
            summary = "This is a summary for comment delete endpoint",
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

    @DeleteMapping("/comments/{commentId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ApiResponse deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
        return new ApiResponse("Comment has been deleted successfully", true);
    }


    @Operation(
            description = "Update endpoint for Comments",
            summary = "This is a summary for comment update endpoint",
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
    @PutMapping("/comments/{commentId}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer commentId) {
        CommentDto updateComment = this.commentService.updateComment(commentId);
        return new ResponseEntity<CommentDto>(updateComment, HttpStatus.OK);
    }


    @Operation(
            description = "Get endpoint for Comments",
            summary = "This is a summary for comment get endpoint",
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
    @GetMapping("/blogs/{blogId}/comments")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<CommentDto>> getCommentsByBlog(@PathVariable Integer blogId) {
        List<CommentDto> comments = this.commentService.getCommentsByBlog(blogId);
        return new ResponseEntity<List<CommentDto>>(comments, HttpStatus.OK);

    }


    @Operation(
            description = "Get endpoint for Comments",
            summary = "This is a summary for comment get endpoint",
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
    @GetMapping("/users/{userId}/comments")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<CommentDto>> getCommentsByUser(@PathVariable Integer userId) {
        List<CommentDto> comments = this.commentService.getCommentsByUser(userId);
        return new ResponseEntity<List<CommentDto>>(comments, HttpStatus.OK);

    }


    @Operation(
            description = "Post endpoint for Comments",
            summary = "This is a summary for comment post endpoint",
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
    @PostMapping("/{userId}/{parentCommentId}/replies")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<CommentDto> createReply(@RequestBody Comment reply, @PathVariable Integer userId, @PathVariable Integer parentCommentId) {
        CommentDto createdReply = commentService.createReply(reply, userId, parentCommentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReply);
    }

}






