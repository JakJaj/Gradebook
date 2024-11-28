package com.jj.Gradebook.entity;


import com.jj.Gradebook.entity.Student_Parent.StudentParent;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="Parents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="parent_id")
    private Long parentId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "parent")
    private Set<StudentParent> studentParents;

    public Parent(String firstName, String lastName, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
    }

}
