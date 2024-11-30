package com.jj.Gradebook.controller.response.students;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.StudentDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentResponse extends BaseResponse {
    private StudentDTO student;
}
