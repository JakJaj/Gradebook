package com.jj.Gradebook.controller.request.teachers;


import com.jj.Gradebook.dto.TeacherDTO;
import lombok.Data;

@Data
public class UpdateTeacherDetailsRequest {
    private TeacherDTO teacher;
}
