package com.task2.blogapp.services;////package com.task2.blogapp.services;

import com.task2.blogapp.entities.Comment;
import com.task2.blogapp.payload.CommentDto;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment, Integer blogId, Integer userId);

    void deleteComment(Integer commentId);

    CommentDto updateComment(Integer commentId);

    List<CommentDto> getCommentsByBlog(Integer blogId);

    List<CommentDto> getCommentsByUser(Integer userId);

    CommentDto createReply(Comment reply, Integer userId, Integer parentCommentId);


}







