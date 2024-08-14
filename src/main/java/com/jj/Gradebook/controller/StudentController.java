package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.service.student.StudentService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @CrossOrigin
    @GetMapping("/students")
    public List<JSONObject> findAll(){
        List<JSONObject> output = new ArrayList<>();

        Optional<List<Student>> students = Optional.ofNullable(studentService.findAll());

        if(students.isPresent()) {
            for (Student student : students.get()) {
                JSONObject row = new JSONObject();
                row.put("ID", student.getStudentId());
                row.put("FirstName", student.getFirstName());
                row.put("LastName", student.getLastName());
                row.put("Email", student.getUser().getEmail());
                row.put("BirthDate", new SimpleDateFormat("dd.MM.yyyy").format(student.getDateOfBirth().getTime()));
                row.put("Address", student.getCity() + ", " + student.getStreet() + " " + student.getHouseNumber());
                row.put("Class", student.getStudentClass().getClassName());
                row.put("Status", student.getUser().isEnabled());
                output.add(row);
            }
        }
        else {
            output.add(new JSONObject());
        }
        return output;
    }

    @CrossOrigin
    @GetMapping("/students/{id}")
    public Student findById(@PathVariable int id){
        return studentService.findById(id);
    }

    @CrossOrigin
    @PostMapping("/students")
    public Student save(@RequestBody Student student){
        return studentService.save(student);
    }

    @CrossOrigin
    @DeleteMapping("/students/{id}")
    public void deleteById(@PathVariable int id){
        studentService.deleteById(id);
    }
}
