package com.jj.Gradebook.entity;


import jakarta.persistence.*;

import java.time.Year;

@Entity
@Table(name = "Classes")
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

    public Class() {
    }

    public Class(int classId, String className, String teacherId, Year start_year) {
        this.classId = classId;
        this.className = className;
        this.teacherId = teacherId;
        this.start_year = start_year;
    }

    public Class(String className, String teacherId, Year start_year) {
        this.className = className;
        this.teacherId = teacherId;
        this.start_year = start_year;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Year getStart_year() {
        return start_year;
    }

    public void setStart_year(Year start_year) {
        this.start_year = start_year;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", start_year=" + start_year +
                '}';
    }
}
