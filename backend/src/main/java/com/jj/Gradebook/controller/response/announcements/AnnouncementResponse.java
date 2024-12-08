package com.jj.Gradebook.controller.response.announcements;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.AnnouncementDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AnnouncementResponse extends BaseResponse {
    private AnnouncementDTO announcement;
}
