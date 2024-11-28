package com.jj.Gradebook.controller.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
class BaseResponse {
    private String status;
    private String message;
}
