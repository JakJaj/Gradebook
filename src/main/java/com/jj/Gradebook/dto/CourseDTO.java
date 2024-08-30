package com.jj.Gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CourseDTO {
    Long id;
    String courseName;
    String teacherName;
    String description;
}
