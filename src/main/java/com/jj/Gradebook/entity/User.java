package com.jj.Gradebook.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "salt")
    private String salt;

    @Column(name = "token")
    private String token;

    @Column(name="enabled")
    private boolean enabled;

    @Column(name="role")
    private String role;

    public User(String password, String email, String pesel, String salt, String token, boolean enabled, String role) {
        this.password = password;
        this.enabled = enabled;
        this.role = role;
        this.pesel = pesel;
    }
}

