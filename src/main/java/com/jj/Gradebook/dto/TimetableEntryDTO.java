package com.jj.Gradebook.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TimetableEntryDTO {
    private Long timetableID;
    private String courseName;
    private Long classID;
    private String startTime;
    private String endTime;
    private String classroom;
    private String teacherName;
}
