package com.jj.Gradebook.controller.request;

import com.jj.Gradebook.dto.TeacherDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RegisterTeacherRequest extends RegisterRequest{
    private TeacherDTO teacher;
}
