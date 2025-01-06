package com.jj.Gradebook.controller.response.auth;

import com.jj.Gradebook.controller.response.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthenticationResponse extends BaseResponse {
    private String token;
    private String role;
    private Long id;
    private String password;
}
