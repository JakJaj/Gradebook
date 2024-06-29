package com.jj.Gradebook.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Grades")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Grade(Course course, int mark, Student student, String description, int magnitude) {
        this.course = course;
        this.mark = mark;
        this.student = student;
        this.description = description;
        this.magnitude = magnitude;
    }
}
