package com.jj.Gradebook.controller;

import com.jj.Gradebook.dto.TeacherDTO;
import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
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
    public ResponseEntity<List<TeacherDTO>> findAll(){
        List<TeacherDTO> teacherList = teacherService.findAll();
        return  ResponseEntity.ok(teacherList);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherDTO> findById(@PathVariable Long id) throws EntityNotFoundException {
            TeacherDTO teacher = teacherService.findById(id);
            return ResponseEntity.ok(teacher);
    }

    @PostMapping("/teachers")
    public ResponseEntity<TeacherDTO> save(@RequestBody Teacher teacher) throws EntityAlreadyExistException {
        TeacherDTO teacherToSave = teacherService.save(teacher);
        return ResponseEntity.ok(teacherToSave);
    }

    @DeleteMapping("/teachers/{id}")
    private ResponseEntity<Void> deleteById(@PathVariable Long id) throws EntityNotFoundException {
        teacherService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
