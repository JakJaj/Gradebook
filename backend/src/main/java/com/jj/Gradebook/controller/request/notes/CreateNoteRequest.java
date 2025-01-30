package com.jj.Gradebook.controller.request.notes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateNoteRequest {
    private Long studentID;
    private Long courseID;
    private String title;
    private String description;
    private String date;
}
