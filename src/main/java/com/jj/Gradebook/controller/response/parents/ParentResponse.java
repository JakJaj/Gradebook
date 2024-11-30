package com.jj.Gradebook.controller.response.parents;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.ParentDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParentResponse extends BaseResponse {
    private ParentDTO parent;
}
