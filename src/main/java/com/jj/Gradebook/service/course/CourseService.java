package com.jj.Gradebook.service.course;


import com.jj.Gradebook.dto.CourseDTO;
import com.jj.Gradebook.entity.Course;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityListEmptyException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;

import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll() throws EntityListEmptyException;
    CourseDTO findById(Long id) throws EntityNotFoundException;
    CourseDTO save(Course course) throws EntityAlreadyExistException;
    void deleteById(Long id) throws EntityNotFoundException;
}
