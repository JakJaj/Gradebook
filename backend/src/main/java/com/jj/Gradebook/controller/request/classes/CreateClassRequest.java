package com.jj.Gradebook.controller.request.classes;

import lombok.Data;

@Data
public class CreateClassRequest {
    private String className;
    private int startYear;
    private Long teacherID;
}
