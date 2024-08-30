package com.jj.Gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ParentDTO {
    Long id;
    String firstName;
    String lastName;
    String email;
}
