package com.jj.Gradebook.service.course;

import com.jj.Gradebook.dao.CourseRepository;
import com.jj.Gradebook.entity.Course;
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
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(int id) {
        Optional<Course> result = courseRepository.findById(id);

        Course course;
        if(result.isPresent()){
            course = result.get();
        }
        else { //TODO: FIND BETTER APPROACH
            throw new RuntimeException("No course with id - " + id);
        }
        return course;
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        courseRepository.deleteById(id);
    }
}
