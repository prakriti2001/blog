package com.task2.blogapp.mappers;

import com.task2.blogapp.entities.Blog;
import com.task2.blogapp.payload.BlogDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", uses = CommentMapper.class)
public interface BlogMapper {
    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);

    @Mapping(target = "comments")
    BlogDto toDto(Blog blog);

    Blog toEntity(BlogDto dto);

    List<BlogDto> blogListDto(List<Blog> blogs);
}
