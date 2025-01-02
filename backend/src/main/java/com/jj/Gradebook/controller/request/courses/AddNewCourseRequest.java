package com.jj.Gradebook.controller.request.courses;

import lombok.Data;

@Data
public class AddNewCourseRequest {
    private String courseName;
    private String courseDescription;
    private Long teacherID;
}
