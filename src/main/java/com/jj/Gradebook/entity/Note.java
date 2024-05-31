package com.jj.Gradebook.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private int noteId;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "timetable_id")
    private int timetableId;

    @Column(name = "description")
    private String description;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public Note(int studentId, int timetableId, String description, LocalDateTime dateTime) {
        this.studentId = studentId;
        this.timetableId = timetableId;
        this.description = description;
        this.dateTime = dateTime;
    }
}
