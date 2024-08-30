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

/**
 * Class that is an implementation of a service that is being used to get / set / delete a course data from a students database table
 */
@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;

    /**
     * Business logic used for getting all of existing courses from a database
     * @return list of all instances of CoursesDTOs that exists in a database
     * @throws EntityListEmptyException exception that will be thrown when a database table is empty
     */
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

    /**
     *  Business logic used for finding a specific course by provided course id
     * @param id id of a course that will be used to get a course from a database
     * @return instance of CourseDTO that was received from a database
     * @throws EntityNotFoundException exception that will be thrown when provided id won't match any id in database
     */
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

    /**
     * Business logic used for creating a new course in the database
     * @param course course data that needs to be placed in a database
     * @return data of a created course instance
     * @throws EntityAlreadyExistException exception that will be thrown when a provided course's name with teacher's id exists in a database
     */
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

    /**
     * Business logic used for deleting a course from a database by provided id
     * @param id id of a course that is meant to be deleted from a database
     * @throws EntityNotFoundException exception that will be thrown when provided id won't match any id in database
     **/
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
