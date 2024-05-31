package com.jj.Gradebook.entity.Student_Parent;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
public class StudentParentId implements Serializable {

    private int studentId;
    private int parentId;
}
