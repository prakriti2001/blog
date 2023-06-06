package com.task2.blogapp.servicesImpl;

import com.task2.blogapp.entities.Blog;
import com.task2.blogapp.entities.Comment;
import com.task2.blogapp.entities.User;
import com.task2.blogapp.exception.ResourceNotFoundException;
import com.task2.blogapp.mappers.CommentMapper;
import com.task2.blogapp.payload.CommentDto;
import com.task2.blogapp.repositories.BlogRepo;
import com.task2.blogapp.repositories.CommentRepo;
import com.task2.blogapp.repositories.UserRepo;
import com.task2.blogapp.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {
    private final BlogRepo blogRepo;
    private final CommentRepo commentRepo;
    private final CommentMapper commentMapper;
    private final UserRepo userRepo;

    public CommentServiceImpl(BlogRepo blogRepo, CommentRepo commentRepo, CommentMapper commentMapper, UserRepo userRepo) {
        this.blogRepo = blogRepo;
        this.commentRepo = commentRepo;
        this.commentMapper = commentMapper;
        this.userRepo = userRepo;
    }

    @Override
    public Comment createComment(Comment comment, Integer blogId, Integer userId) {
        Blog blog = this.blogRepo.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog", "blog id", blogId));
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

        comment.setBlog(blog);
        comment.setUser(user);
        return this.commentRepo.save(comment);

    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comm = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
        this.commentRepo.delete(comm);
    }

    @Override
    public CommentDto updateComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
        comment.setContent(comment.getContent());
        Comment updatedComment = this.commentRepo.save(comment);
        return this.commentMapper.toDto(updatedComment);
    }

    @Override
    public List<CommentDto> getCommentsByBlog(Integer blogId) {
        Blog blog = this.blogRepo.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog", "blog Id", blogId));
        List<Comment> comments = this.commentRepo.findByBlog(blog);
        return comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getCommentsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
        List<Comment> comments = this.commentRepo.findByUser(user);
        return comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto createReply(Comment reply, Integer userId, Integer parentCommentId) {
        Comment parentComment = commentRepo.findById(parentCommentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "parentComment Id", parentCommentId));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
        reply.setUser(user);

        parentComment.getCommentReply().add(reply);
        commentRepo.save(parentComment);
        return commentMapper.toDto(parentComment);

    }
}











