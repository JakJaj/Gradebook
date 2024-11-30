package com.jj.Gradebook.controller.teachers;

import com.jj.Gradebook.controller.response.teachers.TeacherResponse;
import com.jj.Gradebook.controller.response.teachers.TeachersResponse;
import com.jj.Gradebook.service.teachers.TeachersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeachersController{

    private final TeachersService teachersService;

    @GetMapping()
    public ResponseEntity<TeachersResponse> getAllTeachers(){
        return ResponseEntity.ok(teachersService.getAllTeachers());
    }

    @GetMapping("/{teacherID}")
    public ResponseEntity<TeacherResponse> getTeacherByID(@PathVariable Long teacherID){
        return ResponseEntity.ok(teachersService.getTeacherByID(teacherID));
    }
}
