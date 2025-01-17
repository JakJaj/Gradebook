package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUser_UserId(Long userID);
    List<Student> findStudentsByStudentClass_ClassId(Long classID);
}
