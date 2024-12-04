package com.jj.Gradebook.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GradeDTO {
    private Long gradeID;
    private Long studentID;
    private int mark;
    private int magnitude;
    private String description;
    private String date;
}
