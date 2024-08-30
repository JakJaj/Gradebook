package com.jj.Gradebook.service.course;

import com.jj.Gradebook.dao.CourseRepository;
import com.jj.Gradebook.dto.CourseDTO;
import com.jj.Gradebook.entity.Course;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityListEmptyException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;


    @Override
    public List<CourseDTO> findAll() throws EntityListEmptyException {
        List<Course> data = courseRepository.findAll();
        List<CourseDTO> result = new ArrayList<>();

        if (data.isEmpty()){
            throw new EntityListEmptyException("List of courses is empty!");
        }

        for (Course course : data){
            result.add(getCourseDTO(course));
        }
        return result;
    }

    @Override
    public CourseDTO findById(Long id) throws EntityNotFoundException {
        Optional<Course> result = courseRepository.findById(id);

        if(result.isPresent()){
            return getCourseDTO(result.get());
        }
        else {
            throw new EntityNotFoundException("No course with id - " + id);
        }

    }

    @Override
    @Transactional
    public CourseDTO save(Course course) throws EntityAlreadyExistException {
        Optional<Course> existingCourse = courseRepository.findCourseByCourseNameAndTeacher_TeacherId(course.getCourseName(), course.getTeacher().getTeacherId());

        if (existingCourse.isEmpty()){
            Course savedCourse = courseRepository.save(course);
            return getCourseDTO(savedCourse);
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

    /**
     *  Template generator of CourseDTO from Course
     *  @param course the course instance with all the information about course
     *  @return       courseDTO which includes just a data that can be seen by an end users
     **/
    private CourseDTO getCourseDTO(Course course){
        return new CourseDTO(
                course.getCourseId(),
                course.getCourseName(),
                course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName(),
                course.getDescription()
        );
    }
}
