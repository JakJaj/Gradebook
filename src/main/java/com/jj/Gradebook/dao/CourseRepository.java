package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findCourseByCourseNameAndTeacher_TeacherId(String courseName, Long teacherId);
}
