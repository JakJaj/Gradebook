package com.jj.Gradebook.service.course;


import com.jj.Gradebook.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
    Course findById(Long id);
    Course save(Course course);
    void deleteById(Long id);
}
