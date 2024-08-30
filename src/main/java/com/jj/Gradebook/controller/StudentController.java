package com.jj.Gradebook.controller;

import com.jj.Gradebook.dto.StudentDTO;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import com.jj.Gradebook.service.student.StudentService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @CrossOrigin
    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> findAll(){
        List<StudentDTO> students = studentService.findAll();
        return ResponseEntity.ok(students);
    }

    @CrossOrigin
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable Long id) throws EntityNotFoundException {
        StudentDTO student = studentService.findById(id);
        return ResponseEntity.ok(student);
    }

    @CrossOrigin
    @PostMapping("/students")
    public ResponseEntity<StudentDTO> save(@RequestBody Student student) throws EntityAlreadyExistException {
        StudentDTO savedStudent = studentService.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    @CrossOrigin
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws EntityNotFoundException {
        studentService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
