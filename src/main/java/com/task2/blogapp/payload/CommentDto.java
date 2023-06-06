package com.task2.blogapp.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private int id;
    private String content;

    private UserDto user;

    private Set<CommentDto> commentReply;


}
