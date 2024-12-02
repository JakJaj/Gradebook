package com.jj.Gradebook.controller.classes;

import com.jj.Gradebook.controller.response.classes.ClassResponse;
import com.jj.Gradebook.controller.response.classes.ClassesResponse;
import com.jj.Gradebook.controller.response.classes.TimetableResponse;
import com.jj.Gradebook.controller.response.students.StudentsResponse;
import com.jj.Gradebook.service.classes.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{classID}")
    public ResponseEntity<ClassResponse> getClassByClassID(@PathVariable Long classID){
        return ResponseEntity.ok(classesService.getClassByClassID(classID));
    }

    @GetMapping("/{clasID}/timetables")
    public ResponseEntity<TimetableResponse> getTimetablesOfTheClass(@PathVariable Long clasID){
        return ResponseEntity.ok(classesService.getTimetableOfClass(clasID));
    }

    @GetMapping("/{classID}/students")
    public ResponseEntity<StudentsResponse> getStudentsOfTheClass(@PathVariable Long classID){
        return ResponseEntity.ok(classesService.getStudentsOfClass(classID));
    }
}
