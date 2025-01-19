package com.jj.Gradebook.controller.response.notes;

import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.NoteDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StudentNotesResponse extends BaseResponse {
    private List<NoteDTO> notes;
    private Long noteID;
}
