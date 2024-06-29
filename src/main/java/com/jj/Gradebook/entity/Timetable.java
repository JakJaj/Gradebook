package com.jj.Gradebook.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "Timetables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timetable_id")
    private Long timetableId;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "classroom_number")
    private String classroomNumber;

    @Column(name = "day_of_week")
    private int dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class clas;

    public Timetable(Course course, Class clas, LocalTime startTime, LocalTime endTime, String classroomNumber, int dayOfWeek) {
        this.course = course;
        this.clas = clas;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroomNumber = classroomNumber;
        this.dayOfWeek = dayOfWeek;
    }
}
