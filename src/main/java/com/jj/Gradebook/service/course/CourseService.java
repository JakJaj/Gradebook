package com.jj.Gradebook.service.course;


import com.jj.Gradebook.entity.Course;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityListEmptyException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;

import java.util.List;

public interface CourseService {
    List<Course> findAll() throws EntityListEmptyException;
    Course findById(Long id) throws EntityNotFoundException;
    Course save(Course course) throws EntityAlreadyExistException;
    void deleteById(Long id) throws EntityNotFoundException;
}
