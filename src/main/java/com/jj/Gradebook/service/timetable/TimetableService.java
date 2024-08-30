package com.jj.Gradebook.service.timetable;

import com.jj.Gradebook.entity.Timetable;

import java.util.List;

public interface TimetableService {
    List<Timetable> findAll();
    Timetable findById(Long id);
    Timetable save(Timetable timetable);
    void deleteById(Long id);
}
