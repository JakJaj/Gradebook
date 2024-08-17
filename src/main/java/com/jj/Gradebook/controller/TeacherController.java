package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.service.teacher.TeacherService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/teachers")
    public Teacher save(@RequestBody Teacher teacher){
        return teacherService.save(teacher);
    }

    @DeleteMapping("/teachers/{id}")
    private void deleteById(@PathVariable int id){
        teacherService.deleteById(id);
    }
}
