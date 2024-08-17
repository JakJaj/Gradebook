package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findTeacherByUserPesel(String pesel);
}
