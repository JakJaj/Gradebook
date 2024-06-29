package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Course;
import com.jj.Gradebook.service.course.CourseService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public List<JSONObject> findAll(){
        List<Course> courses = new ArrayList<>();
        List<JSONObject> output = new ArrayList<>();

        for(Course course: courses){
            JSONObject row = new JSONObject();
            row.put("ID", course.getCourseId());
            row.put("Course", course.getCourseType());
            row.put("Teacher", course.getTeacher());
            row.put("Description", course.getDescription());
            output.add(row);
        }
        return  output;
    }
}
