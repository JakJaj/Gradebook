package com.jj.Gradebook.controller.response.exception;

import com.jj.Gradebook.controller.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExceptionResponse extends BaseResponse {
    private String errorCode;
}
