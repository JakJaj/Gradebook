package com.jj.Gradebook.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Announcements")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Announcements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private int announcementId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "teacher_id")
    private int teacher_id;

    public Announcements(String title, String content, LocalDateTime dateTime, int teacher_id) {
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
        this.teacher_id = teacher_id;
    }

}
