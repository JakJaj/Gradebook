package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findTeacherByUserPesel(String pesel);

}
