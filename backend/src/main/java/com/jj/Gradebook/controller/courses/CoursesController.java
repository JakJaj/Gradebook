package com.jj.Gradebook.controller.courses;

import com.jj.Gradebook.controller.response.courses.CourseResponse;
import com.jj.Gradebook.controller.response.courses.CoursesResponse;
import com.jj.Gradebook.service.courses.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CoursesController {
    private final CoursesService coursesService;


    @GetMapping("")
    public ResponseEntity<CoursesResponse> getAllCourses(){
        return ResponseEntity.ok(coursesService.getAllCourses());
    }

    @GetMapping("/{courseID}")
    public ResponseEntity<CourseResponse> getCourseByCourseID(@PathVariable Long courseID){
        return ResponseEntity.ok(coursesService.getCourseByCourseID(courseID));
    }

    @GetMapping("/teachers/{teacherID}")
    public ResponseEntity<CoursesResponse> getCoursesOfTeacher(@PathVariable Long teacherID){
        return ResponseEntity.ok(coursesService.getCoursesOfTeacher(teacherID));
    }
}
