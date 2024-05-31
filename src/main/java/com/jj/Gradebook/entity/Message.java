package com.jj.Gradebook.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private int messageId;

    @Column(name = "sender_id")
    private int senderId;

    @Column(name = "receiver_id")
    private int receiver_id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public Message(int senderId, int receiver_id, String title, String content, LocalDateTime dateTime) {
        this.senderId = senderId;
        this.receiver_id = receiver_id;
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
    }
}
