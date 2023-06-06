package com.task2.blogapp.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties("user")
public class BlogDto {
    private int Id;
    private String title;
    private String Description;
    private Date addedDate;
    private Set<CommentDto> comments;
}
