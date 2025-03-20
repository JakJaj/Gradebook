package com.jj.Gradebook.controller.classes;

import com.jj.Gradebook.controller.request.classes.CreateClassRequest;
import com.jj.Gradebook.controller.request.classes.UpdateClassDetailsRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.attendance.ClassAttendanceResponse;
import com.jj.Gradebook.controller.response.classes.ClassCoursesResponse;
import com.jj.Gradebook.controller.response.classes.ClassResponse;
import com.jj.Gradebook.controller.response.classes.ClassesResponse;
import com.jj.Gradebook.controller.response.classes.TimetableResponse;
import com.jj.Gradebook.controller.response.students.StudentsResponse;
import com.jj.Gradebook.service.attendances.AttendancesService;
import com.jj.Gradebook.service.classes.ClassesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class ClassController {

    private final ClassesService classesService;
    private final AttendancesService attendancesService;

    @GetMapping("")
    public ResponseEntity<ClassesResponse> getAllClasses(){
        log.info("GET /classes - Fetching all classes");
        return ResponseEntity.ok(classesService.getAllClasses());
    }

    @GetMapping("/{classID}")
    public ResponseEntity<ClassResponse> getClassByClassID(@PathVariable Long classID){
        log.info("GET /classes/{} - Fetching class by ID", classID);
        return ResponseEntity.ok(classesService.getClassByClassID(classID));
    }

    @GetMapping("/{clasID}/timetables")
    public ResponseEntity<TimetableResponse> getTimetablesOfTheClass(@PathVariable Long clasID){
        log.info("GET /classes/{}/timetables - Fetching timetable of the class", clasID);
        return ResponseEntity.ok(classesService.getTimetableOfClass(clasID));
    }

    @GetMapping("/{classID}/students")
    public ResponseEntity<StudentsResponse> getStudentsOfTheClass(@PathVariable Long classID){
        log.info("GET /classes/{}/students - Fetching students of the class", classID);
        return ResponseEntity.ok(classesService.getStudentsOfClass(classID));
    }

    @GetMapping("/{classID}/attendances")
    public ResponseEntity<ClassAttendanceResponse> getAttendanceOfClass(@PathVariable Long classID){
        log.info("GET /classes/{}/attendances - Fetching attendance of the class", classID);
        return ResponseEntity.ok(attendancesService.getAttendanceOfClass(classID));
    }


    @PostMapping()
    public ResponseEntity<ClassResponse> createNewClass(@RequestBody CreateClassRequest request){
        return ResponseEntity.ok(classesService.createNewClass(request));
    }

    @PostMapping("/{classID}/teachers/{teacherID}")
    public ResponseEntity<ClassResponse> addTeacherToClass(@PathVariable Long classID, @PathVariable Long teacherID){
        return ResponseEntity.ok(classesService.addTeacherToClass(classID, teacherID));
    }

    @PutMapping()
    public ResponseEntity<ClassResponse> updateClassDetails(@RequestBody UpdateClassDetailsRequest request){
        return ResponseEntity.ok(classesService.updateClassDetails(request));
    }

    @DeleteMapping("/{classID}")
    public ResponseEntity<BaseResponse> deleteClass(@PathVariable Long classID){
        return ResponseEntity.ok(classesService.deleteClass(classID));
    }
}
