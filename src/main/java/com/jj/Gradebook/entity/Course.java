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
    private Long courseId;

    @Column(name = "course_type")
    private String courseType;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Course(String courseType, Teacher teacher, String description) {
        this.courseType = courseType;
        this.teacher = teacher;
        this.description = description;
    }
}
