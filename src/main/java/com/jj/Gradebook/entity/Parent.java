package com.jj.Gradebook.entity;


import jakarta.persistence.*;

@Entity
@Table(name="Parents")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="parent_id")
    private int parentId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name="user_id")
    private String userId;

    
}
