package com.jj.Gradebook.controller.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthenticationResponse extends BaseResponse{
    private String token;
}
