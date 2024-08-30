package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import com.jj.Gradebook.service.classes.ClassService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Class> findAll(){
        List<Class> output = classService.findAll();

        return output;
    }

    @GetMapping("/classes/{id}")
    public Class findById(@PathVariable int id) throws EntityNotFoundException {
        return classService.findById(id);
    }

    @PostMapping("/classes")
    public Class save(@RequestBody Class theClass) throws EntityAlreadyExistException {
        return classService.save(theClass);
    }

    @DeleteMapping("/classes/{id}")
    public void deleteById(@PathVariable int id) throws EntityNotFoundException {
        classService.deleteById(id);
    }
}
