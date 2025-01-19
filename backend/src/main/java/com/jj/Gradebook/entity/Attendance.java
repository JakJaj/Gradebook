package com.jj.Gradebook.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Attendances")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long attendanceId;

    @Column(name = "date_time")
    private Date dateTime;

    @Column(name = "status")
    private String status;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne()
    @JoinColumn(name = "course_id")
    private Course course;

}
