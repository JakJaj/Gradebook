package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findStudentByUser_Pesel(String pesel);

}
