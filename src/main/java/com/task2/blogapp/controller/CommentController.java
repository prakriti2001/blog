package com.task2.blogapp.controller;

import com.task2.blogapp.entities.Comment;
import com.task2.blogapp.payload.ApiResponse;
import com.task2.blogapp.payload.CommentDto;
import com.task2.blogapp.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/users/{userId}/blogs/{blogId}/comments")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @PathVariable Integer blogId, @PathVariable Integer userId) {
        Comment createComment = this.commentService.createComment(comment, blogId, userId);
        return new ResponseEntity<Comment>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ApiResponse deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
        return new ApiResponse("Comment has been deleted successfully", true);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer commentId) {
        CommentDto updateComment = this.commentService.updateComment(commentId);
        return new ResponseEntity<CommentDto>(updateComment, HttpStatus.OK);
    }


    @GetMapping("/blogs/{blogId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByBlog(@PathVariable Integer blogId) {
        List<CommentDto> comments = this.commentService.getCommentsByBlog(blogId);
        return new ResponseEntity<List<CommentDto>>(comments, HttpStatus.OK);

    }

    @GetMapping("/users/{userId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByUser(@PathVariable Integer userId) {
        List<CommentDto> comments = this.commentService.getCommentsByUser(userId);
        return new ResponseEntity<List<CommentDto>>(comments, HttpStatus.OK);

    }


    @PostMapping("/{userId}/{parentCommentId}/replies")
    public ResponseEntity<CommentDto> createReply(@RequestBody Comment reply, @PathVariable Integer userId, @PathVariable Integer parentCommentId) {
        CommentDto createdReply = commentService.createReply(reply, userId, parentCommentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReply);
    }

}






