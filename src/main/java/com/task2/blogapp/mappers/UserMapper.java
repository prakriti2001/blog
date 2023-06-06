package com.task2.blogapp.mappers;

import com.task2.blogapp.entities.User;
import com.task2.blogapp.payload.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BlogMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapUserToUserDto(User user);

    User mapUserDtoToUser(UserDto userDto);

    List<UserDto> userDTOList(List<User> users);

//    @Mappings({
//            @Mapping(target = "user", ignore = true)
//    })
//    BlogDto mapBlogToBlogDto(Blog blog);
//
//    @Mappings({
//            @Mapping(target = "user", ignore = true)
//    })
//    CommentDto mapCommentToCommentDto(Comment comment);
//
//    @Mappings({
//            @Mapping(target = "user", ignore = true)
//    })
//    ReplyDto mapReplyToReplyDto(Reply reply);
//
//    @Mappings({
//            @Mapping(target = "comments", source = "comments"),
//            @Mapping(target = "replies", source = "replies"),
//            @Mapping(target = "blogs", source = "blogs")
//    })
//    List<CommentDto> mapCommentsToCommentDtos(List<Comment> comments);
//
//    List<ReplyDto> mapRepliesToReplyDtos(List<Reply> replies);
}