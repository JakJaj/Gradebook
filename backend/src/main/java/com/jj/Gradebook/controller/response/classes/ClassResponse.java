package com.jj.Gradebook.controller.response.classes;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.ClassDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClassResponse extends BaseResponse {
    private ClassDTO theClass;
}
