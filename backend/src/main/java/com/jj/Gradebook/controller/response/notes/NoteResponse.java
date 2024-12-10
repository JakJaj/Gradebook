package com.jj.Gradebook.controller.response.notes;


import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.dto.NoteDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class NoteResponse extends BaseResponse {
    private NoteDTO note;
}
