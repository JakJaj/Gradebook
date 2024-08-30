package com.jj.Gradebook.controller;

import com.jj.Gradebook.dto.ClassDTO;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import com.jj.Gradebook.service.classes.ClassService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClassController {
    private ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @CrossOrigin
    @GetMapping("/classes")
    public ResponseEntity<List<ClassDTO>> findAll(){
        List<ClassDTO> classes = classService.findAll();

        return ResponseEntity.ok(classes);
    }

    @GetMapping("/classes/{id}")
    public ResponseEntity<ClassDTO> findById(@PathVariable Long id) throws EntityNotFoundException {
        ClassDTO theClass = classService.findById(id);
        return ResponseEntity.ok(theClass);
    }

    @PostMapping("/classes")
    public ResponseEntity<ClassDTO> save(@RequestBody Class theClass) throws EntityAlreadyExistException {
        ClassDTO savedClass = classService.save(theClass);
        return ResponseEntity.ok(savedClass);
    }

    @DeleteMapping("/classes/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws EntityNotFoundException {
        classService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
