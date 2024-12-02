package com.jj.Gradebook.controller.response.courses;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.CourseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class CoursesResponse extends BaseResponse {
    private List<CourseDTO> courses;
}
