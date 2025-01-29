package com.jj.Gradebook.controller.request.notes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateNoteDetailsRequest {
    private Long noteID;
    private String description;
    private String title;
    private String date;
}
