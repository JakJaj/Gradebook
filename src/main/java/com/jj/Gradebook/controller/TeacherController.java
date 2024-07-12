package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.service.teacher.TeacherService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    public List<JSONObject> findAll(){
        List<Teacher> teachers = teacherService.findAll();
        List<JSONObject> output = new ArrayList<>();

        for (Teacher teacher: teachers){
            JSONObject row = new JSONObject();
            row.put("ID", teacher.getTeacherId());
            row.put("FirstName", teacher.getFirstName());
            row.put("LastName", teacher.getLastName());
            row.put("Email", teacher.getUser().getEmail());
            row.put("BirthDate", teacher.getDateOfBirth().get(Calendar.DATE) + "-" + (teacher.getDateOfBirth().get(Calendar.MONTH) + 1 ) + "-" + teacher.getDateOfBirth().get(Calendar.YEAR));
            row.put("EmploymentDate", teacher.getDateOfEmployment().get(Calendar.DATE) + "-" + (teacher.getDateOfEmployment().get(Calendar.MONTH) + 1 ) + "-" + teacher.getDateOfEmployment().get(Calendar.YEAR));
            row.put("Status", teacher.getUser().isEnabled());
            output.add(row);
        }
        return output;
    }

    @GetMapping("/teachers/{id}")
    public Teacher findById(@PathVariable int id){
        return teacherService.findById(id);
    }

    @PostMapping("/teacher")
    public Teacher save(@PathVariable Teacher teacher){
        return teacherService.save(teacher);
    }

    @DeleteMapping("/teacher/{id}")
    private void deleteById(@PathVariable int id){
        teacherService.deleteById(id);
    }
}
