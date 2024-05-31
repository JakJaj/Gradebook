package com.jj.Gradebook.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Course_Types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course_Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_type_id")
    private int courseTypeId;

    @Column(name = "course_name")
    private String courseName;

    public Course_Type(String courseName) {
        this.courseName = courseName;
    }
}
