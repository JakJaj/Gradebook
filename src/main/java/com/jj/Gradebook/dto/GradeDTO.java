package com.jj.Gradebook.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class GradeDTO {
    private Long gradeId;
    private int mark;
    private String description;
    private int magnitude;
}
