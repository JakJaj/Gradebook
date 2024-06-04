package com.jj.Gradebook.service.student_parent;

import com.jj.Gradebook.entity.Student_Parent.StudentParent;
import com.jj.Gradebook.entity.Student_Parent.StudentParentId;

import java.util.List;

public interface StudentParentService {
    List<StudentParent> findAll();
    StudentParent findById(StudentParentId studentParentId);
    StudentParent save(StudentParent studentParent);
    void deleteById(StudentParentId studentParentId);
}
