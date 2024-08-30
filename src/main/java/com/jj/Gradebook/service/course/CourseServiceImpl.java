package com.jj.Gradebook.service.course;

import com.jj.Gradebook.dao.CourseRepository;
import com.jj.Gradebook.entity.Course;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityListEmptyException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;


    @Override
    public List<Course> findAll() throws EntityListEmptyException {
        List<Course> data = courseRepository.findAll();

        if (data.isEmpty()){
            throw new EntityListEmptyException("List of courses is empty!");
        }

        return data;
    }

    @Override
    public Course findById(Long id) throws EntityNotFoundException {
        Optional<Course> result = courseRepository.findById(id);

        Course course;
        if(result.isPresent()){
            course = result.get();
        }
        else {
            throw new EntityNotFoundException("No course with id - " + id);
        }
        return course;
    }

    @Override
    @Transactional
    public Course save(Course course) throws EntityAlreadyExistException {
        Optional<Course> existingCourse = courseRepository.findCourseByCourseNameAndTeacher_TeacherId(course.getCourseName(), course.getTeacher().getTeacherId());

        if (existingCourse.isEmpty()){

            return courseRepository.save(course);
        }
        else {
            throw new EntityAlreadyExistException("Course already exists!");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws EntityNotFoundException {
        Optional<Course> existingCourse = courseRepository.findById(id);
        if (existingCourse.isPresent()){
            courseRepository.deleteById(id);
        }
        else {
            throw new EntityNotFoundException("No course with id - " + id);
        }
    }
}
