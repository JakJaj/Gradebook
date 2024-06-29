package com.jj.Gradebook.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.CascadePoint;

import java.util.Date;

@Entity
@Table(name = "Students")
@Getter
@Setter
@ToString
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
    private int houseNumber;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class studentClass;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Student(String firstName, String lastName, Date dateOfBirth, String city, String street, int houseNumber, Class studentClass, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.studentClass = studentClass;
        this.user = user;
    }
}
