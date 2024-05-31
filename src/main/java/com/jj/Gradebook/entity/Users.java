package com.jj.Gradebook.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int id;

    @Column(name="password")
    private String password;

    @Column(name="enabled")
    private boolean enabled;

    @Column(name="role")
    private String role;

}


