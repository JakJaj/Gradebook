package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Parent;
import com.jj.Gradebook.entity.User;
import com.jj.Gradebook.service.parent.ParentService;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ParentController {

    private ParentService parentService;

    @Autowired
    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @CrossOrigin
    @GetMapping("/parents")
    public List<Parent> findAll(){
        List<Parent> output = parentService.findAll();

        return output;
    }

    @GetMapping("/parents/{id}")
    public Parent findById(@PathVariable Long id){
        return parentService.findById(id);
    }

    @PostMapping("/parents")
    public Parent save(@RequestBody Parent parent){
        return parentService.save(parent);
    }

    @DeleteMapping("/parents/{id}")
    public void deleteById(@PathVariable Long id){
        parentService.deleteById(id);
    }
}
