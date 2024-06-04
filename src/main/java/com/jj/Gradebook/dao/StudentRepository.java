package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
