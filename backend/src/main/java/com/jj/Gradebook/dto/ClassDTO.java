package com.jj.Gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ClassDTO {
    private Long classID;
    private String className;
    private int startYear;
    private TeacherDTO tutor;
}
