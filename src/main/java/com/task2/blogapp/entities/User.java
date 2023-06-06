package com.task2.blogapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;

    @ManyToOne(fetch = FetchType.LAZY)

    private Address address;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Blog> blogs = new ArrayList<>();
}
