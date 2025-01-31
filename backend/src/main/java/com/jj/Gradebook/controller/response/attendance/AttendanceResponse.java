package com.jj.Gradebook.controller.response.attendance;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.AttendanceDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder()
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AttendanceResponse extends BaseResponse {
    private AttendanceDTO attendance;
}
