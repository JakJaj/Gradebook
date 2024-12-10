package com.jj.Gradebook.controller.request.attendances;

import lombok.Data;

@Data
public class UpdateAttendanceDetailsRequest {
    private Long attendanceID;
    private String date;
    private String status;
}
