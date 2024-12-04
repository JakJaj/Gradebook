package com.jj.Gradebook.controller.timetables;

import com.jj.Gradebook.controller.response.classes.TimetableResponse;
import com.jj.Gradebook.service.timetable.TimetablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timetables")
@RequiredArgsConstructor
public class TimetablesController {

    private final TimetablesService timetablesService;


    @GetMapping("/users/{userID}")
    public ResponseEntity<TimetableResponse> getUsersTimetable(@PathVariable Long userID){
        return ResponseEntity.ok(timetablesService.getUsersTimetable(userID));
    }
}
