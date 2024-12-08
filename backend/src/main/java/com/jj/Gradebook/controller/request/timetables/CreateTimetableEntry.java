package com.jj.Gradebook.controller.request.timetables;

import lombok.Data;

import java.time.LocalTime;

@Data
public class CreateTimetableEntry {
    private Long courseID;
    private Long classID;
    private LocalTime startTime;
    private LocalTime endTime;
    private String classroomNumber;
    private int dayOfWeek;
}
