package com.jj.Gradebook.controller.request.grades;

import lombok.Data;

@Data
public class CreateGradeRequest {
    private Long courseID;
    private int mark;
    private String description;
    private int magnitude;
    private String date;
}
