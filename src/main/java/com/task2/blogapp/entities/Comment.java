package com.task2.blogapp.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Comment> commentReply;


}



