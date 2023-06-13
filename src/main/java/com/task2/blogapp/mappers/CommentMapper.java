package com.task2.blogapp.mappers;

import com.task2.blogapp.entities.Comment;
import com.task2.blogapp.entities.User;
import com.task2.blogapp.payload.CommentDto;
import com.task2.blogapp.payload.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);

    @Named("noBlogs")
    @Mapping(target = "blogs", ignore = true)
    UserDto userToUserDto(User user);


    @Mapping(target = "user", qualifiedByName = "noBlogs")
    CommentDto toDto(Comment comment);

    Comment toEntity(CommentDto dto);

    Set<CommentDto> repliesToRepliesDto(Set<Comment> replies);


}


// ...


// ...
