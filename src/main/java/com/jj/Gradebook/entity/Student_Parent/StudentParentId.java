package com.jj.Gradebook.entity.Student_Parent;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class StudentParentId implements Serializable {

    private int studentId;
    private int parentId;
}
