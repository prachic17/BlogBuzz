package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post_blog")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private Long id;

    @Column(name="title" , nullable = false)
    private String title;

    @Column(name="content" , nullable = false)
    private String content;

    @Column(name="description" , nullable = false)
    private String description;

    @OneToMany(mappedBy = "posts" , cascade = CascadeType.ALL , orphanRemoval = true)
    private Set<Comments> comments = new HashSet<>();
}
