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

    public Grade(int courseId, int mark, int student_id, String description, int magnitude) {
        this.courseId = courseId;
        this.mark = mark;
        this.student_id = student_id;
        this.description = description;
        this.magnitude = magnitude;
    }
}
