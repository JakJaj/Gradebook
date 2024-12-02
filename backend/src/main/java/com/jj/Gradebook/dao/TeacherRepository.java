package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByUser_UserId(Long userID);
}
