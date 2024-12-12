package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {
    void deleteAllByClassId(Long classID);
    List<Class> findClassesByTeacher_TeacherId(Long teacherID);
}
