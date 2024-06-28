package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Course;
import com.jj.Gradebook.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Course> findAll(){
        return courseService.findAll();
    }
}
