package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
