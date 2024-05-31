package com.jj.Gradebook.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "course_type_id")
    private int courseTypeId;

    @Column(name = "teacher_id")
    private int teacherId;

    @Column(name = "description")
    private String description;


}
