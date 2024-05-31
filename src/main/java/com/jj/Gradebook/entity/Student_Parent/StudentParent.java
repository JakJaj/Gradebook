package com.jj.Gradebook.entity.Student_Parent;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Students_Parents")
@IdClass(StudentParentId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentParent {

    @Id
    @Column(name = "student_id")
    private int studentId;

    @Id
    @Column(name = "parent_id")
    private int parentId;
}
