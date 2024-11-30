package com.jj.Gradebook.controller.teachers;

import com.jj.Gradebook.controller.response.teachers.TeachersResponse;
import com.jj.Gradebook.service.teachers.TeachersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeachersController{

    private final TeachersService teachersService;

    @GetMapping()
    public ResponseEntity<TeachersResponse> getAllTeachers(){
        return ResponseEntity.ok(teachersService.getAllTeachers());
    }
}
