package com.jj.Gradebook.service.attendance;

import com.jj.Gradebook.entity.Attendance;

import java.util.List;

public interface AttendanceService {
    List<Attendance> findAll();
    Attendance findById(int id);
    Attendance save(Attendance attendance);
    void deleteById(int id);
}
