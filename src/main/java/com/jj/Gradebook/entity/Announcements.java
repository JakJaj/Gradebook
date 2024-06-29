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
    private Long announcementId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne()
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Announcements(String title, String content, LocalDateTime dateTime, Teacher teacher) {
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
        this.teacher = teacher;
    }

}
