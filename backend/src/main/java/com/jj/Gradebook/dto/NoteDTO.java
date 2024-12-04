package com.jj.Gradebook.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NoteDTO {
    private Long noteID;
    private String description;
    private String date;
    private Long studentID;
    private TimetableEntryDTO timetableEntry;
}
