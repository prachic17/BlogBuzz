package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private Long id;

    @Column(name="name" , nullable = false)
    private String name;

    @Column(name="username" , nullable = false)
    private String username;

    @Column(name="password" , nullable = false)
    private String password;

    @Column(name="email" , nullable = false)
    private String email;


    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name = "user_role_mapping" , joinColumns = @JoinColumn(name = "user_id" , referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id" , referencedColumnName = "id"))
    private Set<Roles> roles;



}
