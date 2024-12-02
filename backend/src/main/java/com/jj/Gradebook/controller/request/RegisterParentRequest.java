package com.jj.Gradebook.controller.request;


import com.jj.Gradebook.dto.ParentDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RegisterParentRequest extends RegisterRequest{
    private ParentDTO parent;
}
