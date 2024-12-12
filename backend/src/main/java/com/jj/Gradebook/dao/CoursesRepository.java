package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Course, Long> {
    List<Course> findCourseByTeacher_TeacherId(Long teacherID);
    void deleteCoursesByTeacher_TeacherId(Long teacherID);
}
