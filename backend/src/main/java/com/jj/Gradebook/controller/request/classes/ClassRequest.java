package com.jj.Gradebook.controller.request.classes;

import com.jj.Gradebook.dto.TeacherDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClassRequest {
    private Long classID;
    private String className;
    private int startYear;
    private Long tutorID;
}

