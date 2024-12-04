package com.jj.Gradebook.controller.response.students;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.GradeDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class StudentGradesResponse extends BaseResponse {
    private HashMap<String, List<GradeDTO>> grades;
}
