package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/students/{id}")
    public Student findById(@PathVariable int id){
        return studentService.findById(id);
    }

    @PostMapping("/students")
    public Student save(@PathVariable Student student){
        return studentService.save(student);
    }

    @DeleteMapping("/students/{id}")
    public void deleteById(@PathVariable int id){
        studentService.deleteById(id);
    }
}
