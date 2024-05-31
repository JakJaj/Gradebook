package com.jj.Gradebook.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "Grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private int gradeId;

    @Column(name = "course_id")
    private int courseId;

    @Column(name = "mark")
    private int mark;

    @Column(name = "student_id")
    private int student_id;

    @Column(name = "description")
    private String description;

    @Column(name = "magnitude")
    private int magnitude;

    
}
