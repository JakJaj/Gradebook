package com.jj.Gradebook.entity.Student_Parent;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class StudentParentId implements Serializable {

    private Long studentId;
    private Long parentId;
}
