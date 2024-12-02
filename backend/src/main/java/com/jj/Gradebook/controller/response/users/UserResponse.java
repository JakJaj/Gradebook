package com.jj.Gradebook.controller.response.users;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends BaseResponse {
    private Long userID;
    private String email;
    private String pesel;
    private Role role;
    private String firstName;
    private String lastName;
    private Long subClassID;
}
