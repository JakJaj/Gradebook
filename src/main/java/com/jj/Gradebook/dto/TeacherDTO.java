package com.jj.Gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@AllArgsConstructor
@Getter
@Setter
public class TeacherDTO {

    private Long teacherId;
    private String firstName;
    private String lastName;
    private String pesel;
    private String email;
    private String dateOfBirth;
    private String dateOfEmployment;
    private String status;

}
