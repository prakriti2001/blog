package com.task2.blogapp.servicesImpl;

import com.task2.blogapp.entities.Blog;
import com.task2.blogapp.entities.User;
import com.task2.blogapp.exception.ResourceNotFoundException;
import com.task2.blogapp.mappers.BlogMapper;
import com.task2.blogapp.payload.BlogDto;
import com.task2.blogapp.repositories.BlogRepo;
import com.task2.blogapp.repositories.UserRepo;
import com.task2.blogapp.services.BlogService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepo blogRepo;
    private final BlogMapper blogMapper;
    private final UserRepo userRepo;

    public BlogServiceImpl(BlogRepo blogRepo, BlogMapper blogMapper, UserRepo userRepo) {
        this.blogRepo = blogRepo;
        this.blogMapper = blogMapper;
        this.userRepo = userRepo;
    }

    @Override
    public Blog createBlog(Blog blog, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));

        blog.setAddedDate(new Date());
        blog.setUser(user);
        return blogRepo.save(blog);

    }

    @Override
    public BlogDto updateBlog(Integer blogId) {
        Blog blog = this.blogRepo.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog", "blog id", blogId));
        blog.setTitle(blog.getTitle());
        blog.setDescription(blog.getDescription());
        Blog updatedBlog = this.blogRepo.save(blog);
        BlogDto blogDto1 = blogMapper.toDto(updatedBlog);
        return blogDto1;

    }

    @Override
    public void deleteBlog(Integer blogId) {
        Blog blog = this.blogRepo.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog", "blog id", blogId));
        this.blogRepo.delete(blog);

    }

    @Override
    public List<BlogDto> getAllBlog() {
        List<Blog> allBlogs = this.blogRepo.findAll();
        return blogMapper.blogListDto(allBlogs);

    }

    @Override
    public BlogDto getBlogById(Integer blogId) {
        Blog blog = this.blogRepo.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog", "blog id", blogId));
        return blogMapper.toDto(blog);
    }

    @Override
    public List<BlogDto> getBlogsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        List<Blog> blogs = this.blogRepo.findByUser(user);
        return blogMapper.blogListDto(blogs);
    }


}
