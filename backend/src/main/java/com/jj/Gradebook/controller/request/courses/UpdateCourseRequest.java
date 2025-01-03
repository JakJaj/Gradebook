package com.jj.Gradebook.controller.request.courses;

import lombok.Data;

@Data
public class UpdateCourseRequest {
    private Long courseID;
    private String courseName;
    private String courseDescription;
    private Long teacherID;
}
