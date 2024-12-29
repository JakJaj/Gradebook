package com.jj.Gradebook.controller.students;

import com.jj.Gradebook.controller.request.attendances.CreateAttendanceRequest;
import com.jj.Gradebook.controller.request.attendances.UpdateAttendanceDetailsRequest;
import com.jj.Gradebook.controller.request.grades.CreateGradeRequest;
import com.jj.Gradebook.controller.request.grades.UpdateGradeDetailsRequest;
import com.jj.Gradebook.controller.request.notes.CreateNoteRequest;
import com.jj.Gradebook.controller.request.notes.UpdateNoteDetailsRequest;
import com.jj.Gradebook.controller.request.students.UpdateStudentDetailsRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.attendance.AttendanceResponse;
import com.jj.Gradebook.controller.response.grades.GradeResponse;
import com.jj.Gradebook.controller.response.notes.NoteResponse;
import com.jj.Gradebook.controller.response.notes.StudentNotesResponse;
import com.jj.Gradebook.controller.response.parents.ParentsResponse;
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

    @GetMapping("/{studentID}/parents")
    public ResponseEntity<ParentsResponse> getParentsOfStudent(@PathVariable Long studentID){
        return ResponseEntity.ok(studentsService.getParentsOfStudents(studentID));
    }

    @PostMapping("/{studentID}/grades")
    public ResponseEntity<StudentGradesResponse> createNewGrade(@PathVariable Long studentID, @RequestBody CreateGradeRequest request){
        return ResponseEntity.ok(gradesService.createNewGrade(request, studentID));
    }

    @PostMapping("/{studentID}/attendances")
    public ResponseEntity<StudentAttendancesResponse> createNewAttendance(@PathVariable Long studentID, @RequestBody CreateAttendanceRequest request){
        return ResponseEntity.ok(attendancesService.createNewAttendance(studentID, request));
    }

    @PostMapping("/{studentID}/notes")
    public ResponseEntity<StudentNotesResponse> createNewNote(@PathVariable Long studentID, @RequestBody CreateNoteRequest request){
        return ResponseEntity.ok(notesService.createNewNote(studentID, request));
    }

    @PostMapping("/{studentID}/classes/{classID}")
    public ResponseEntity<BaseResponse> addStudentToClass(@PathVariable Long studentID, @PathVariable Long classID){
        return ResponseEntity.ok(studentsService.addStudentToClass(studentID, classID));
    }
    @PutMapping()
    @CrossOrigin
    public ResponseEntity<StudentResponse> updateStudentDetails(@RequestBody UpdateStudentDetailsRequest request){
        return ResponseEntity.ok(studentsService.updateStudentDetails(request));
    }

    @PutMapping("/{studentID}/grades")
    public ResponseEntity<GradeResponse> updateGradeDetail(@PathVariable Long studentID, @RequestBody UpdateGradeDetailsRequest request){
        return ResponseEntity.ok(gradesService.updateGradeDetails(studentID, request));
    }

    @PutMapping("/{studentID}/attendances")
    public ResponseEntity<AttendanceResponse> updateAttendanceDetail(@PathVariable Long studentID, @RequestBody UpdateAttendanceDetailsRequest request){
        return ResponseEntity.ok(attendancesService.updateAttendanceDetails(studentID, request));
    }

    @PutMapping("/{studentID}/notes")
    public ResponseEntity<NoteResponse> updateNoteDetails(@PathVariable Long studentID, @RequestBody UpdateNoteDetailsRequest request){
        return ResponseEntity.ok(notesService.updateNoteDetails(studentID, request));
    }

    @DeleteMapping("/{studentID}/grades/{gradeID}")
    public ResponseEntity<StudentGradesResponse> deleteGrade(@PathVariable Long studentID, @PathVariable Long gradeID){
        return ResponseEntity.ok(gradesService.deleteGrade(studentID, gradeID));
    }

    @DeleteMapping("/{studentID}/attendances/{attendanceID}")
    public ResponseEntity<StudentAttendancesResponse> deleteAttendance(@PathVariable Long studentID, @PathVariable Long attendanceID){
        return ResponseEntity.ok(attendancesService.deleteAttendance(studentID, attendanceID));
    }

    @DeleteMapping("/{studentID}/notes/{noteID}")
    public ResponseEntity<StudentNotesResponse> deleteNote(@PathVariable Long studentID, @PathVariable Long noteID){
        return ResponseEntity.ok(notesService.deleteNote(studentID, noteID));
    }

    @DeleteMapping("/{studentID}")
    public ResponseEntity<BaseResponse> deleteStudent(@PathVariable Long studentID){
        return ResponseEntity.ok(studentsService.deleteStudent(studentID));
    }
}
