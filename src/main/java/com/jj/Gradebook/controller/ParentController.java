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
    public List<JSONObject> findAll(){
        List<Parent> parents = parentService.findAll();
        List<JSONObject> output = new ArrayList<>();

        for (Parent parent: parents){
            JSONObject row = new JSONObject();
            row.put("ID", parent.getParentId());
            row.put("FirstName", parent.getFirstName());
            row.put("LastName", parent.getLastName());
            row.put("Email", parent.getUser().getEmail());
            output.add(row);
        }
        return output;
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
