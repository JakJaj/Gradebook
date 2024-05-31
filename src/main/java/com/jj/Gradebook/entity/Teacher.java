package com.jj.Gradebook.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="teacher_id")
    private int teacherId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Column(name="email")
    private String email;

    @Column(name="date_of_employment")
    private Date dateOfEmployment;

    @Column(name="user_id")
    private int userId;

    public Teacher() {
    }

    public Teacher(int teacherId, String firstName, String lastName, Date dateOfBirth, String email, Date dateOfEmployment, int userId) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.dateOfEmployment = dateOfEmployment;
        this.userId = userId;
    }

    public Teacher(String firstName, String lastName, Date dateOfBirth, String email, Date dateOfEmployment, int userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.dateOfEmployment = dateOfEmployment;
        this.userId = userId;
    }

    
}
