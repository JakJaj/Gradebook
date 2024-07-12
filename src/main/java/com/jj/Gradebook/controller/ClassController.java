package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.service.classes.ClassService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    public List<JSONObject> findAll(){
        List<Class> classes = new ArrayList<>();
        List<JSONObject> output = new ArrayList<>();

        for(Class clas: classes){
            JSONObject row = new JSONObject();
            row.put("ID", clas.getClassId());
            row.put("ClassName", clas.getClassName());
            row.put("Tutor", clas.getTeacher().getFirstName() + " " + clas.getTeacher().getLastName());
            row.put("Year", clas.getStart_year());
            row.put("Status", LocalDate.now().getYear() == clas.getStart_year().getValue());
            output.add(row);
        }
        return output;
    }

    @GetMapping("/classes/{id}")
    public Class findById(@PathVariable int id){
        return classService.findById(id);
    }

    @PostMapping("/classes")
    public Class save(@RequestBody Class theClass){
        return classService.save(theClass);
    }

    @DeleteMapping("/classes/{id}")
    public void deleteById(@PathVariable int id){
        classService.deleteById(id);
    }
}
