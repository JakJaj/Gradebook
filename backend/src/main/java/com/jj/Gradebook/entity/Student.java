package com.jj.Gradebook.entity;


import com.jj.Gradebook.entity.Student_Parent.StudentParent;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.CascadePoint;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Students")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id")
    private Class studentClass;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy="student")
    @EqualsAndHashCode.Exclude
    private Set<StudentParent> studentParents;
}
