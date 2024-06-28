package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.service.classes.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return classService.findAll();
    }

    @GetMapping("/classes/{id}")
    public Class findById(@PathVariable int id){
        return classService.findById(id);
    }

    @PostMapping("/classes")
    public Class save(@PathVariable Class theClass){
        return classService.save(theClass);
    }

    @DeleteMapping("/classes/{id}")
    public void deleteById(@PathVariable int id){
        classService.deleteById(id);
    }
}
