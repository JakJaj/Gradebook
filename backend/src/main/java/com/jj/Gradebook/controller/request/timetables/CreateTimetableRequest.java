package com.jj.Gradebook.controller.request.timetables;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class CreateTimetableRequest {
    private List<CreateTimetableEntry> timetables;
}
