package com.jj.Gradebook.controller.request.attendances;

import lombok.Data;

import java.util.Date;

@Data
public class CreateAttendanceRequest {
    private Long studentID;
    private Long courseID;
    private String status;
    private String date;
}
