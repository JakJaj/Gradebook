package com.jj.Gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TeacherDTO {
    private Long teacherID;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String dateOfEmployment;
    private String email;
    private String pesel;
}
