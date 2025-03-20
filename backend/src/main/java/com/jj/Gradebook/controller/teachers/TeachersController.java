package com.jj.Gradebook.controller.teachers;

import com.jj.Gradebook.controller.request.teachers.UpdateTeacherDetailsRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.classes.TimetableResponse;
import com.jj.Gradebook.controller.response.teachers.TeacherResponse;
import com.jj.Gradebook.controller.response.teachers.TeachersResponse;
import com.jj.Gradebook.dao.TimetableRepository;
import com.jj.Gradebook.service.teachers.TeachersService;
import com.jj.Gradebook.service.timetable.TimetablesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
@Slf4j
public class TeachersController{

    private final TeachersService teachersService;

    @GetMapping()
    @CrossOrigin
    public ResponseEntity<TeachersResponse> getAllTeachers(){
        log.info("GET /teachers - Fetching all teachers");
        return ResponseEntity.ok(teachersService.getAllTeachers());
    }

    @GetMapping("/{teacherID}")
    @CrossOrigin
    public ResponseEntity<TeacherResponse> getTeacherByID(@PathVariable Long teacherID){
        log.info("GET /teachers/{} - Fetching teacher by ID", teacherID);
        return ResponseEntity.ok(teachersService.getTeacherByID(teacherID));
    }

    @PutMapping()
    @CrossOrigin
    public ResponseEntity<TeacherResponse> updateTeacherDetails(@RequestBody UpdateTeacherDetailsRequest request){
        log.info("PUT /teachers - Updating teacher details");
        return ResponseEntity.ok(teachersService.updateTeacherDetails(request));
    }

    @DeleteMapping("/{teacherID}")
    @CrossOrigin
    public ResponseEntity<BaseResponse> deleteTeacher(@PathVariable Long teacherID){
        log.info("DELETE /teachers/{} - Deleting teacher", teacherID);
        return ResponseEntity.ok(teachersService.deleteTeacher(teacherID));
    }
}
