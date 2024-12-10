package com.jj.Gradebook.controller.request.announcements;

import lombok.Data;

@Data
public class UpdateAnnouncementDetailsRequest {
    private Long announcementID;
    private String title;
    private String content;
    private String date;
}
