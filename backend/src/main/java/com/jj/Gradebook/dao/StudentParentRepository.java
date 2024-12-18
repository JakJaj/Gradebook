package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Student_Parent.StudentParent;
import com.jj.Gradebook.entity.Student_Parent.StudentParentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentParentRepository extends JpaRepository<StudentParent, StudentParentId> {
    List<StudentParent> getStudentParentByParent_ParentId(Long parentID);

    void deleteStudentParentsByStudent_StudentId(Long studentID);
    void deleteStudentParentByParent_ParentId(Long parentID);

    Optional<StudentParent> getStudentParentByStudent_StudentIdAndParent_ParentId(Long studentID, Long parentID);
}
