package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Student_Parent.StudentParent;
import com.jj.Gradebook.entity.Student_Parent.StudentParentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Student_ParentRepository extends JpaRepository<StudentParent, StudentParentId> {
}
