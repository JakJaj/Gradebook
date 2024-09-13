package com.jj.Gradebook.controller;

import com.jj.Gradebook.dto.GradeDTO;
import com.jj.Gradebook.dto.StudentDTO;
import com.jj.Gradebook.entity.Grade;
import com.jj.Gradebook.service.grade.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GradeController {

    private GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/grades/{studentID}")
    public ResponseEntity<HashMap<String, List<GradeDTO>>> getStudentGradesGroupedByCourseByStudentId(@PathVariable Long studentID){
        HashMap<String, List<GradeDTO>> grades = gradeService.getGradesGroupedByCourseByStudentId(studentID);
        return ResponseEntity.ok(grades);
    }
    //GET Oceny zmapowane na zajęcia = {uczen = {ocenyUcznia}, uczen = {ocenyUcznia} (po zajeciach)
    @GetMapping("/grades/{classID}/{courseID}")
    public ResponseEntity<HashMap<String, HashMap<Long, List<GradeDTO>>>> getStudnetsGradesGroupedByCoursesByClassIDAndCourseID (@PathVariable Long classID, @PathVariable Long courseID){
        HashMap<String, HashMap<Long, List<GradeDTO>>> grades = gradeService.getStudnetsGradesGroupedByCoursesByClassIDAndCourseID(classID, courseID);
        return ResponseEntity.ok(grades);
    }
    //GET pojedyncza ocena po id oceny dla szczegółów oceny i do edycji oceny

    //POST Wstawianie oceny po ID ucznia i ID zajęć

    //PATCH Edycja wybranej oceny po ID oceny (wcześniej dostanej z GET

    //DELETE Pojedyncza ocena po ID
}
