package com.jj.Gradebook.entity.Student_Parent;


import com.jj.Gradebook.entity.Parent;
import com.jj.Gradebook.entity.Student;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Students_Parents")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudentParent {

    @EmbeddedId
    private StudentParentId id;

    @ManyToOne()
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;


    @ManyToOne()
    @MapsId("parentId")
    @JoinColumn(name = "parent_id")
    private Parent parent;
}
