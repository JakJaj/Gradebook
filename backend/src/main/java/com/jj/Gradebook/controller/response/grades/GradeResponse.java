package com.jj.Gradebook.controller.response.grades;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.GradeDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class GradeResponse extends BaseResponse {
    private GradeDTO grade;
}
