package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    Optional<List<Grade>> findAllByStudent_StudentId(Long studentId);
}
