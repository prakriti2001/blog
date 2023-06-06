package com.task2.blogapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private Date addedDate;

    @ManyToOne(fetch = FetchType.LAZY)

    private User user;

    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    private Set<Comment> comments = new HashSet<>();


}
