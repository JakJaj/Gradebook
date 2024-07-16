package com.jj.Gradebook.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Year;

@Entity
@Table(name = "Classes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long classId;

    @Column(name = "class_name")
    private String className;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "start_year")
    private Year start_year;

    @Column(name = "enabled")
    private boolean status;

    public Class(String className, Teacher teacher, Year start_year, boolean status) {
        this.className = className;
        this.teacher = teacher;
        this.start_year = start_year;
        this.status = status;
    }

}
