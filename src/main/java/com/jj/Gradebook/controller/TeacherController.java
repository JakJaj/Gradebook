package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import com.jj.Gradebook.service.teacher.TeacherService;
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
public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @CrossOrigin
    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> findAll(){
        List<Teacher> teacherList = teacherService.findAll();
        return  ResponseEntity.ok(teacherList);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> findById(@PathVariable int id) throws EntityNotFoundException {
            Teacher teacher = teacherService.findById(id);
            return ResponseEntity.ok(teacher);
    }

    @PostMapping("/teachers")
    public Teacher save(@RequestBody Teacher teacher){
        Optional<Teacher> teacherToSave = Optional.ofNullable(teacherService.findByPesel(teacher.getUser().getPesel()));

        return teacherService.save(teacher);
    }

    @DeleteMapping("/teachers/{id}")
    private void deleteById(@PathVariable int id){
        teacherService.deleteById(id);
    }
}
