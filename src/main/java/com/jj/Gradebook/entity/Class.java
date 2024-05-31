package com.jj.Gradebook.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@Entity
@Table(name = "Classes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private int classId;

    @Column(name = "class_name")
    private String className;

    @Column(name = "teacher_id")
    private String teacherId;

    @Column(name = "start_year")
    private Year start_year;

    public Class(String className, String teacherId, Year start_year) {
        this.className = className;
        this.teacherId = teacherId;
        this.start_year = start_year;
    }

}
