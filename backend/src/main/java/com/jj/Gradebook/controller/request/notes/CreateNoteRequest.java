package com.jj.Gradebook.controller.request.notes;

import lombok.Data;

@Data
public class CreateNoteRequest {
    private Long studentID;
    private Long timetableID;
    private String title;
    private String description;
    private String date;
}
