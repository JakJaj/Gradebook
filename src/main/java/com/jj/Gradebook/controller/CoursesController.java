package com.jj.Gradebook.controller;

import com.jj.Gradebook.dto.CourseDTO;
import com.jj.Gradebook.entity.Course;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityListEmptyException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import com.jj.Gradebook.service.course.CourseService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CourseDTO>> findAll() throws EntityListEmptyException {
        List<CourseDTO> output = courseService.findAll();
        return ResponseEntity.ok(output);
    }

    @CrossOrigin
    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable Long id) throws EntityNotFoundException {
        CourseDTO output = courseService.findById(id);
        return ResponseEntity.ok(output);
    }

    @CrossOrigin
    @PostMapping("/courses")
    public ResponseEntity<CourseDTO> save(@RequestBody Course course) throws EntityAlreadyExistException {
        CourseDTO output = courseService.save(course);
        return ResponseEntity.ok(output);
    }

    @CrossOrigin
    @DeleteMapping("/coruses/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws EntityNotFoundException {
        courseService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
