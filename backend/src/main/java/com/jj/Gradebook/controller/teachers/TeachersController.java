package com.jj.Gradebook.controller.teachers;

import com.jj.Gradebook.controller.response.classes.TimetableResponse;
import com.jj.Gradebook.controller.response.teachers.TeacherResponse;
import com.jj.Gradebook.controller.response.teachers.TeachersResponse;
import com.jj.Gradebook.dao.TimetableRepository;
import com.jj.Gradebook.service.teachers.TeachersService;
import com.jj.Gradebook.service.timetable.TimetablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeachersController{

    private final TeachersService teachersService;
    private final TimetablesService timetablesService;

    @GetMapping()
    public ResponseEntity<TeachersResponse> getAllTeachers(){
        return ResponseEntity.ok(teachersService.getAllTeachers());
    }

    @GetMapping("/{teacherID}")
    public ResponseEntity<TeacherResponse> getTeacherByID(@PathVariable Long teacherID){
        return ResponseEntity.ok(teachersService.getTeacherByID(teacherID));
    }
}
