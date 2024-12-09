package com.jj.Gradebook.controller.classes;

import com.jj.Gradebook.controller.request.classes.CreateClassRequest;
import com.jj.Gradebook.controller.request.classes.UpdateClassDetailsRequest;
import com.jj.Gradebook.controller.response.attendance.ClassAttendanceResponse;
import com.jj.Gradebook.controller.response.classes.ClassResponse;
import com.jj.Gradebook.controller.response.classes.ClassesResponse;
import com.jj.Gradebook.controller.response.classes.TimetableResponse;
import com.jj.Gradebook.controller.response.students.StudentsResponse;
import com.jj.Gradebook.service.attendances.AttendancesService;
import com.jj.Gradebook.service.classes.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class ClassController {

    private final ClassesService classesService;
    private final AttendancesService attendancesService;

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

    @GetMapping("/{classID}/attendances")
    public ResponseEntity<ClassAttendanceResponse> getAttendanceOfClass(@PathVariable Long classID){
        return ResponseEntity.ok(attendancesService.getAttendanceOfClass(classID));
    }

    @PostMapping()
    public ResponseEntity<ClassResponse> createNewClass(@RequestBody CreateClassRequest request){
        return ResponseEntity.ok(classesService.createNewClass(request));
    }

    @PutMapping()
    public ResponseEntity<ClassResponse> updateClassDetails(@RequestBody UpdateClassDetailsRequest request){
        return ResponseEntity.ok(classesService.updateClassDetails(request));
    }
}
