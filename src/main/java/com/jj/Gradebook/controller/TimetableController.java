package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Timetable;
import com.jj.Gradebook.service.timetable.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TimetableController {
    private TimetableService timetableService;

    @Autowired
    public TimetableController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @GetMapping("/timetables")
    public List<Timetable> findAll(){
        return timetableService.findAll();
    }

    @GetMapping("/timetables/{id}")
    public Timetable findById(@PathVariable int id){
        return timetableService.findById(id);
    }

    @PostMapping("/timetables")
    public Timetable save(@RequestBody Timetable timetable){
        return timetableService.save(timetable);
    }

    @DeleteMapping("/timetables/{id}")
    public void deleteById(@PathVariable int id){
        timetableService.deleteById(id);
    }
}
