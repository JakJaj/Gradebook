package com.jj.Gradebook.controller.response.teachers;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.TeacherDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TeacherResponse extends BaseResponse {
    private TeacherDTO teacher;
}
