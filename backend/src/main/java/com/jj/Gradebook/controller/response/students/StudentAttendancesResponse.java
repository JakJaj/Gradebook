package com.jj.Gradebook.controller.response.students;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.AttendanceDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StudentAttendancesResponse extends BaseResponse {
    private List<AttendanceDTO> attendances;
    private Long attendanceID;
}
