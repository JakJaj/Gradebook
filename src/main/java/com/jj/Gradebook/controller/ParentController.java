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
    public List<JSONObject> findAll(){
        List<JSONObject> output = new ArrayList<>();

        Optional<List<Parent>> parents = Optional.ofNullable(parentService.findAll());

        if (parents.isPresent()) {
            for (Parent parent : parents.get()) {
                JSONObject row = new JSONObject();
                row.put("ID", parent.getParentId());
                row.put("FirstName", parent.getFirstName());
                row.put("LastName", parent.getLastName());
                row.put("Email", parent.getUser().getEmail());
                output.add(row);
            }
        }
        else {
            output.add(new JSONObject());
        }
        return output;
    }

    @GetMapping("/parents/{id}")
    public Parent findById(@PathVariable int id){
        return parentService.findById(id);
    }

    @PostMapping("/parents")
    public Parent save(@RequestBody Parent parent){
        return parentService.save(parent);
    }

    @DeleteMapping("/parents/{id}")
    public void deleteById(@PathVariable int id){
        parentService.deleteById(id);
    }
}
