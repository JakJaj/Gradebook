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
    private int attendanceId;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "status")
    private String status;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "timetable_id")
    private int timetableId;

    public Attendance(LocalDateTime dateTime, String status, int studentId, int timetableId) {
        this.dateTime = dateTime;
        this.status = status;
        this.studentId = studentId;
        this.timetableId = timetableId;
    }
}
