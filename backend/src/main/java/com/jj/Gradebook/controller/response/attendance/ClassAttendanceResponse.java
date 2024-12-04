package com.jj.Gradebook.controller.response.attendance;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.AttendanceDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClassAttendanceResponse extends BaseResponse {
    private HashMap<Long, List<AttendanceDTO>> studentsAttendance;
}
