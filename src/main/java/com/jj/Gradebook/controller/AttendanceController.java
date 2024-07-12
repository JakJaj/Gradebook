package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Attendance;
import com.jj.Gradebook.service.attendance.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AttendanceController {

    private AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/attendances")
    public List<Attendance> findAll(){
        return attendanceService.findAll();
    }

    @GetMapping("/attendances/{id}")
    public Attendance findById(@PathVariable int id){
        return attendanceService.findById(id);
    }

    @PostMapping("/attendances")
    public Attendance save(@RequestBody Attendance attendance){
        return attendanceService.save(attendance);
    }

    @DeleteMapping("/attendances/{id}")
    public void deleteById(@PathVariable int id){
        attendanceService.deleteById(id);
    }
}
