package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Course;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityListEmptyException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import com.jj.Gradebook.service.course.CourseService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CoursesController {

    CourseService courseService;

    @Autowired
    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }


    @CrossOrigin
    @GetMapping("/courses")
    public List<Course> findAll() throws EntityListEmptyException {
        List<Course> output = courseService.findAll();
        return  output;
    }

    @CrossOrigin
    @GetMapping("/courses/{id}")
    public Course findById(@PathVariable Long id) throws EntityNotFoundException {
        Course output = courseService.findById(id);
        return output;
    }

    @CrossOrigin
    @PostMapping("/courses")
    public Course save(@RequestBody Course course) throws EntityAlreadyExistException {
        Course output = courseService.save(course);
        return output;
    }

    @CrossOrigin
    @DeleteMapping("/coruses/{id}")
    public void deleteById(@PathVariable Long id) throws EntityNotFoundException {
        courseService.deleteById(id);
    }
}
