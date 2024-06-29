package com.jj.Gradebook.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Attendances")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long attendanceId;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "status")
    private String status;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "timetable_id")
    private Timetable timetable;

    public Attendance(LocalDateTime dateTime, String status, Student student, Timetable timetable) {
        this.dateTime = dateTime;
        this.status = status;
        this.timetable = timetable;
        this.student = student;
    }
}
