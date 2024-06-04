package com.jj.Gradebook.service.course_type;

import com.jj.Gradebook.dao.CourseTypeRepository;
import com.jj.Gradebook.entity.Course_Type;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Course_TypeServiceImpl implements Course_TypeService{
    private CourseTypeRepository courseTypeRepository;

    @Override
    public List<Course_Type> findAll() {
        return courseTypeRepository.findAll();
    }

    @Override
    public Course_Type findById(int id) {
        Optional<Course_Type> result = courseTypeRepository.findById(id);

        Course_Type courseType;
        if(result.isPresent()){
            courseType = result.get();
        }
        else { //TODO: FIND BETTER APPROACH
            throw new RuntimeException("No course_type with id - " + id);
        }
        return courseType;
    }

    @Override
    @Transactional
    public Course_Type save(Course_Type courseType) {
        return courseTypeRepository.save(courseType);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        courseTypeRepository.deleteById(id);
    }
}
