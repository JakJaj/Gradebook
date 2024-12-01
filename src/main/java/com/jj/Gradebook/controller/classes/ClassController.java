package com.jj.Gradebook.controller.classes;

import com.jj.Gradebook.controller.response.classes.ClassesResponse;
import com.jj.Gradebook.service.classes.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class ClassController {

    private final ClassesService classesService;

    @GetMapping("")
    public ResponseEntity<ClassesResponse> getAllClasses(){
        return ResponseEntity.ok(classesService.getAllClasses());
    }

    
}
