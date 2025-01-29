package com.jj.Gradebook.controller.request.classes;

import com.jj.Gradebook.dto.ClassDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateClassDetailsRequest {
    private ClassRequest theClass;
}
