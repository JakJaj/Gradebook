package com.jj.Gradebook.controller.request.classes;

import com.jj.Gradebook.dto.ClassDTO;
import lombok.Data;

@Data
public class UpdateClassDetailsRequest {
    private ClassRequest theClass;
}
