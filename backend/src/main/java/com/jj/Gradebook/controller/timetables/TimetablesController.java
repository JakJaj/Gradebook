package com.jj.Gradebook.controller.timetables;

import com.jj.Gradebook.controller.request.timetables.CreateTimetableEntry;
import com.jj.Gradebook.controller.request.timetables.CreateTimetableRequest;
import com.jj.Gradebook.controller.response.classes.TimetableResponse;
import com.jj.Gradebook.service.timetable.TimetablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timetables")
@RequiredArgsConstructor
public class TimetablesController {

    private final TimetablesService timetablesService;


    @GetMapping("/users/{userID}")
    public ResponseEntity<TimetableResponse> getUsersTimetable(@PathVariable Long userID){
        return ResponseEntity.ok(timetablesService.getUsersTimetable(userID));
    }

    @PostMapping()
    public ResponseEntity<TimetableResponse> createNewTimetableEntry(@RequestBody CreateTimetableRequest request){
        return ResponseEntity.ok(timetablesService.createNewTimetableEntry(request));
    }

}
