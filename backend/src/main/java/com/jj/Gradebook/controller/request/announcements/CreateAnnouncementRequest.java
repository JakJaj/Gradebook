package com.jj.Gradebook.controller.request.announcements;

import lombok.Data;

@Data
public class CreateAnnouncementRequest {
    private String title;
    private String content;
    private String date;
    private Long teacherID;
}
