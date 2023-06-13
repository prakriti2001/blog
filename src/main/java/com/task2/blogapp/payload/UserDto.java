package com.task2.blogapp.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    private String userName;
    private String email;
    private String password;
    private String about;
    private AddressDto address;
    private List<BlogDto> blogs;


}

