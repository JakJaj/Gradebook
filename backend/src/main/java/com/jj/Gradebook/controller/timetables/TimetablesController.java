package com.jj.Gradebook.controller.timetables;

import com.jj.Gradebook.controller.request.timetables.CreateTimetableEntry;
import com.jj.Gradebook.controller.request.timetables.CreateTimetableRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.classes.TimetableResponse;
import com.jj.Gradebook.service.timetable.TimetablesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timetables")
@RequiredArgsConstructor
@Slf4j
public class TimetablesController {

    private final TimetablesService timetablesService;


    @GetMapping("/users/{userID}")
    @CrossOrigin
    public ResponseEntity<TimetableResponse> getUsersTimetable(@PathVariable Long userID){
        log.info("GET /timetables/users/{} - Fetching timetable of the user", userID);
        return ResponseEntity.ok(timetablesService.getUsersTimetable(userID));
    }

    @GetMapping("/classes/{classID}")
    @CrossOrigin
    public ResponseEntity<TimetableResponse> getClassTimetable(@PathVariable Long classID){
        log.info("GET /timetables/classes/{} - Fetching timetable of the class", classID);
        return ResponseEntity.ok(timetablesService.getClassTimetable(classID));
    }

    @GetMapping("/teachers/{teacherID}")
    @CrossOrigin
    public ResponseEntity<TimetableResponse> getTeacherTimetable(@PathVariable Long teacherID){
        log.info("GET /timetables/teachers/{} - Fetching timetable of the teacher", teacherID);
        return ResponseEntity.ok(timetablesService.getTeacherTimetable(teacherID));
    }

    @PostMapping()
    @CrossOrigin
    public ResponseEntity<TimetableResponse> createNewTimetableEntry(@RequestBody CreateTimetableRequest request){
        log.info("POST /timetables - Creating new timetable entry with request: {}", request);
        return ResponseEntity.ok(timetablesService.createNewTimetableEntry(request));
    }

    @DeleteMapping("/{timetableID}")
    @CrossOrigin
    public ResponseEntity<BaseResponse> deleteTimetableEntry(@PathVariable Long timetableID){
        log.info("DELETE /timetables/{} - Deleting timetable entry", timetableID);
        return ResponseEntity.ok(timetablesService.deleteTimetableEntry(timetableID));
    }

}
