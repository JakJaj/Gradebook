package com.jj.Gradebook.service.course_type;

import com.jj.Gradebook.entity.Course_Type;

import java.util.List;

public interface Course_TypeService {
    List<Course_Type> findAll();
    Course_Type findById(Long id);
    Course_Type save(Course_Type courseType);
    void deleteById(Long id);
}
