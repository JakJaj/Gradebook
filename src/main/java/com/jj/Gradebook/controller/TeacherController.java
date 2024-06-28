package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @CrossOrigin
    @GetMapping("/teachers")
    public List<Teacher> findAll(){
        return teacherService.findAll();
    }

    @GetMapping("/teachers/{id}")
    public Teacher findById(@PathVariable int id){
        return teacherService.findById(id);
    }

    @PostMapping("/teacher")
    public Teacher save(@PathVariable Teacher teacher){
        return teacherService.save(teacher);
    }

    @DeleteMapping("/teacher/{id}")
    private void deleteById(@PathVariable int id){
        teacherService.deleteById(id);
    }
}
