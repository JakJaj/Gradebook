package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Grade;
import com.jj.Gradebook.service.grade.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GradeController {

    private GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/grades")
    public List<Grade> findAll(){
        return gradeService.findAll();
    }

    @GetMapping("/grades/{id}")
    private Grade findById(@PathVariable int id){
        return gradeService.findById(id);
    }

    @PostMapping("/grades")
    public Grade save(@RequestBody Grade grade){
        return gradeService.save(grade);
    }

    @DeleteMapping("/grades/{id}")
    public void deleteById(@PathVariable int id){
        gradeService.deleteById(id);
    }
}
