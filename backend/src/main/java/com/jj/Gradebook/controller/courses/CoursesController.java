package com.jj.Gradebook.controller.courses;

import com.jj.Gradebook.controller.request.courses.AddNewCourseRequest;
import com.jj.Gradebook.controller.request.courses.UpdateCourseRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.courses.CourseResponse;
import com.jj.Gradebook.controller.response.courses.CoursesResponse;
import com.jj.Gradebook.service.courses.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CoursesController {
    private final CoursesService coursesService;


    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<CoursesResponse> getAllCourses(){
        return ResponseEntity.ok(coursesService.getAllCourses());
    }

    @CrossOrigin
    @GetMapping("/{courseID}")
    public ResponseEntity<CourseResponse> getCourseByCourseID(@PathVariable Long courseID){
        return ResponseEntity.ok(coursesService.getCourseByCourseID(courseID));
    }

    @CrossOrigin
    @GetMapping("/teachers/{teacherID}")
    public ResponseEntity<CoursesResponse> getCoursesOfTeacher(@PathVariable Long teacherID){
        return ResponseEntity.ok(coursesService.getCoursesOfTeacher(teacherID));
    }

    @CrossOrigin
    @GetMapping("/{courseID}/classes/{classID}/grades")
    public ResponseEntity<CourseGradesResponse> getGradesInCourseOfClass(@PathVariable Long courseID, @PathVariable Long classID){
        return ResponseEntity.ok(coursesService.getGradesInCourseOfClass(courseID, classID));
    }

    @CrossOrigin
    @GetMapping("/{courseID}/classes/{classID}/attendance")
    public ResponseEntity<CourseAttendanceResponse> getAttendanceInCourseOfStudent(@PathVariable Long courseID, @PathVariable Long classID){
        return ResponseEntity.ok(coursesService.getAttendanceInCourseOfStudent(courseID, classID));
    }

    @CrossOrigin
    @GetMapping("/{courseID}/classes/{classID}/notes")
    public ResponseEntity<CourseNotesResponse> getNotesInCourseOfClass(@PathVariable Long courseID, @PathVariable Long classID){
        return ResponseEntity.ok(coursesService.getNotesInCourseOfClass(courseID, classID));
    }
    @CrossOrigin
    @PostMapping()
    public ResponseEntity<CourseResponse> addNewCourse( @RequestBody AddNewCourseRequest request){
        return ResponseEntity.ok(coursesService.addNewCourse(request));
    }

    @CrossOrigin
    @PutMapping()
    public ResponseEntity<CourseResponse> updateCourse( @RequestBody UpdateCourseRequest request){
        return ResponseEntity.ok(coursesService.updateCourse(request));
    }

    @CrossOrigin
    @DeleteMapping("/{courseID}")
    public ResponseEntity<BaseResponse> deleteCourse(@PathVariable Long courseID){
        return ResponseEntity.ok(coursesService.deleteCourse(courseID));
    }
}
