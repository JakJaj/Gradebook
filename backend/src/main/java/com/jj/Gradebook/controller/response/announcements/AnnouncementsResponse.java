package com.jj.Gradebook.controller.response.announcements;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.AnnouncementDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AnnouncementsResponse extends BaseResponse {
    private List<AnnouncementDTO> announcements;
}
