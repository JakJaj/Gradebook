package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Parent;
import com.jj.Gradebook.entity.User;
import com.jj.Gradebook.service.parent.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParentController {

    private ParentService parentService;

    @Autowired
    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping("/parents")
    public List<Parent> findAll(){
        return parentService.findAll();
    }

    @GetMapping("/parents/{id}")
    public Parent findById(@PathVariable int id){
        return parentService.findById(id);
    }

    @PostMapping("/parents")
    public Parent save(@PathVariable Parent parent){
        return parentService.save(parent);
    }

    @DeleteMapping("/parents/{id}")
    public void deleteById(@PathVariable int id){
        parentService.deleteById(id);
    }
}
