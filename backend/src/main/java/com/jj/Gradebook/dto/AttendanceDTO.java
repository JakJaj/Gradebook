package com.jj.Gradebook.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendanceDTO {
    private Long attendanceID;
    private String status;
    private CourseDTO course;
    private Long studentID;
    private String date;
}
