package com.jj.Gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CourseDTO {
    private Long courseID;
    private String courseName;
    private String description;
    private TeacherDTO tutor;
}
