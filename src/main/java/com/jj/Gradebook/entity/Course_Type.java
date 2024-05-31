package com.jj.Gradebook.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Course_Types")
public class Course_Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_type_id")
    private int courseTypeId;

    @Column(name = "course_name")
    private String courseName;
}
