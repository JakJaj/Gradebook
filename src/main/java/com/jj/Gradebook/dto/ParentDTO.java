package com.jj.Gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ParentDTO {
    private String firstName;
    private String lastName;
}
