package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findGradesByStudent_StudentClass_ClassId(Long classID);
    List<Grade> findGradesByStudent_StudentId(Long studentID);
    void deleteGradesByStudent_StudentId(Long studentID);
}
