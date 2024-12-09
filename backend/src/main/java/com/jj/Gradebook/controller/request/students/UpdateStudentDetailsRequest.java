package com.jj.Gradebook.controller.request.students;

import com.jj.Gradebook.dto.StudentDTO;
import lombok.Data;

@Data
public class UpdateStudentDetailsRequest {
    private StudentDTO student;
}
