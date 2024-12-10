package com.jj.Gradebook.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Grades")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long gradeId;

    @Column(name = "mark")
    private int mark;

    @Column(name = "description")
    private String description;

    @Column(name = "magnitude")
    private int magnitude;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;

}
