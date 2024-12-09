package com.jj.Gradebook.controller.request.parents;

import lombok.Data;

import java.util.List;

@Data
public class AddNewStudentsToParentRequest {
    private List<Long> studentsIDs;
}
