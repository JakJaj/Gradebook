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
    private Year startYear;

    @Column(name = "enabled")
    private boolean status;

    public Class(String className, Teacher teacher, Year startYear, boolean status) {
        this.className = className;
        this.teacher = teacher;
        this.startYear = startYear;
        this.status = status;
    }

}
