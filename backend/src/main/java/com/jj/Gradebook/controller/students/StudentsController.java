package com.jj.Gradebook.controller.students;

import com.jj.Gradebook.controller.response.notes.StudentNotesResponse;
import com.jj.Gradebook.controller.response.students.StudentAttendancesResponse;
import com.jj.Gradebook.controller.response.students.StudentGradesResponse;
import com.jj.Gradebook.controller.response.students.StudentResponse;
import com.jj.Gradebook.controller.response.students.StudentsResponse;
import com.jj.Gradebook.service.attendances.AttendancesService;
import com.jj.Gradebook.service.grades.GradesService;
import com.jj.Gradebook.service.notes.NotesService;
import com.jj.Gradebook.service.students.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@CrossOrigin
public class StudentsController {

    private final StudentsService studentsService;
    private final GradesService gradesService;
    private final NotesService notesService;
    private final AttendancesService attendancesService;

    @GetMapping("")
    public ResponseEntity<StudentsResponse> getAllStudents(){
        return ResponseEntity.ok(studentsService.getAllStudents());
    }

    @GetMapping("/{studentID}")
    public ResponseEntity<StudentResponse> getStudentByStudentID(@PathVariable Long studentID){
        return ResponseEntity.ok(studentsService.getStudentByID(studentID));
    }

    @GetMapping("/{studentID}/grades")
    public ResponseEntity<StudentGradesResponse> getStudentsGrades(@PathVariable Long studentID){
        return ResponseEntity.ok(gradesService.getAllStudentsGrades(studentID));
    }

    @GetMapping("/{studentID}/notes")
    public ResponseEntity<StudentNotesResponse> getStudentsNotes(@PathVariable Long studentID){
        return ResponseEntity.ok(notesService.getAllStudentsNotes(studentID));
    }

    @GetMapping("/{studentID}/attendances")
    public ResponseEntity<StudentAttendancesResponse> getStudentsAttendances(@PathVariable Long studentID){
        return ResponseEntity.ok(attendancesService.getStudentsAttendances(studentID));
    }
}
