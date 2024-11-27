package com.jj.Gradebook.controller.request;

import com.jj.Gradebook.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String password;
    private String email;
    private String pesel;
    private Role role;
}
