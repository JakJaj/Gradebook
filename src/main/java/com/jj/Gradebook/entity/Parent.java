package com.jj.Gradebook.entity;


import com.jj.Gradebook.entity.Student_Parent.StudentParent;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="Parents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="parent_id")
    private Long parentId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @OneToMany(mappedBy = "parent")
    private Set<StudentParent> studentParents;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    public Parent(String firstName, String lastName, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
    }

}
