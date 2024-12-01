package com.jj.Gradebook.controller.students;

import com.jj.Gradebook.controller.response.students.StudentResponse;
import com.jj.Gradebook.controller.response.students.StudentsResponse;
import com.jj.Gradebook.service.students.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsController {

    private final StudentsService studentsService;

    @GetMapping("")
    public ResponseEntity<StudentsResponse> getAllStudents(){
        return ResponseEntity.ok(studentsService.getAllStudents());
    }

    @GetMapping("/{studentID}")
    public ResponseEntity<StudentResponse> getStudentByStudentID(@PathVariable Long studentID){
        return ResponseEntity.ok(studentsService.getStudentByID(studentID));
    }
}
