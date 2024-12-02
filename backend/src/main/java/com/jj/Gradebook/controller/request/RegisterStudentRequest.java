package com.jj.Gradebook.controller.request;

import com.jj.Gradebook.dto.StudentDTO;
import com.jj.Gradebook.entity.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RegisterStudentRequest extends RegisterRequest{
    private StudentDTO student;
}
