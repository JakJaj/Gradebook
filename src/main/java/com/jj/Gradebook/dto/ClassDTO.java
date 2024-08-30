package com.jj.Gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ClassDTO {
    Long id;
    String className;
    String tutorName;
    String year;
    String status;
}
