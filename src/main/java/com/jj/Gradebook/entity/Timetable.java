package com.jj.Gradebook.entity;


import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "Timetables")
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timetable_id")
    private int timetableId;

    @Column(name = "course_id")
    private int courseId;

    @Column(name = "class_id")
    private int classId;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "classroom_number")
    private String classroomNumber;

    @Column(name = "day_of_week")
    private int dayOfWeek;
    
}
