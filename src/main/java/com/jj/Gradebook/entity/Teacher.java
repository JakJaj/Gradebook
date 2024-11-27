package com.jj.Gradebook.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="Teachers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="teacher_id")
    private Long teacherId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="date_of_birth")
    private Calendar dateOfBirth;

    @Column(name="date_of_employment")
    private Calendar dateOfEmployment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

}
