package com.jj.Gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StudentDTO {
    private Long studentID;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String city;
    private String street;
    private String houseNumber;

}
