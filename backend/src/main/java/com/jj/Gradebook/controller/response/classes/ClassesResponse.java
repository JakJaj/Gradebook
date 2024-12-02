package com.jj.Gradebook.controller.response.classes;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.ClassDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClassesResponse extends BaseResponse {
    private List<ClassDTO> classes;
}
