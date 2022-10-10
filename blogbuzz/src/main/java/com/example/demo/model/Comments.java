package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private Long id;

    @Column(name = "emailid" , nullable = false)
    private String emailId;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "body" , nullable = false)
    private String body;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn (name = "post_id" , nullable = false)
    private Posts posts;
}
