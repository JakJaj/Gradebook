package com.jj.Gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class StudentDTO {
    private Long studentID;
    private String firstName;
    private String lastName;
    private String pesel;
    private String email;
    private String dateOfBirth;
    private String address;
    private String studentClass;
    private String status;
}
