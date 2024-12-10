package com.jj.Gradebook.controller.request.notes;

import lombok.Data;

@Data
public class UpdateNoteDetailsRequest {
    private Long noteID;
    private String description;
    private String date;
}
