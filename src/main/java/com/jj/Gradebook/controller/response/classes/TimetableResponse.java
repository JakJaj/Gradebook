package com.jj.Gradebook.controller.response.classes;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.TimetableEntryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class TimetableResponse extends BaseResponse {
    private final HashMap<DayOfWeek, List<TimetableEntryDTO>> timetable;
}
