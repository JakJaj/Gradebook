package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
