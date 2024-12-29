package com.jj.Gradebook.service.users;

import com.jj.Gradebook.config.JwtService;
import com.jj.Gradebook.controller.response.users.UserResponse;
import com.jj.Gradebook.dao.ParentRepository;
import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.dao.TeacherRepository;
import com.jj.Gradebook.dao.UserRepository;
import com.jj.Gradebook.entity.Parent;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.entity.User;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ParentRepository parentRepository;

    public UserResponse getUserDetails(String token) {

        String email = jwtService.extractUsername(token.substring(7));

        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchEntityException("Can't happen tbh"));

        switch (user.getRole()){
            case STUDENT -> {
                Student student = studentRepository.findByUser_UserId(user.getUserId()).orElseThrow(() -> new NoSuchEntityException("Can't happen tbh"));
                return UserResponse.builder()
                        .status("Success")
                        .message("Successfully returning user details for student")
                        .userID(user.getUserId())
                        .email(user.getEmail())
                        .pesel(user.getPesel())
                        .role(user.getRole())
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .subClassID(student.getStudentId())
                        .build();
            }
            case TEACHER -> {
                Teacher teacher = teacherRepository.findByUser_UserId(user.getUserId()).orElseThrow(() -> new NoSuchEntityException("Can't happen tbh"));
                return UserResponse.builder()
                        .status("Success")
                        .message("Successfully returning user details for teacher")
                        .userID(user.getUserId())
                        .email(user.getEmail())
                        .pesel(user.getPesel())
                        .role(user.getRole())
                        .firstName(teacher.getFirstName())
                        .lastName(teacher.getLastName())
                        .subClassID(teacher.getTeacherId())
                        .build();
            }
            case PARENT -> {
                Parent parent = parentRepository.findByUser_UserId(user.getUserId()).orElseThrow(() -> new NoSuchEntityException("Can't happen thb"));
                return UserResponse.builder()
                        .status("Success")
                        .message("Successfully returning user details for parent")
                        .userID(user.getUserId())
                        .email(user.getEmail())
                        .pesel(user.getPesel())
                        .role(user.getRole())
                        .firstName(parent.getFirstName())
                        .lastName(parent.getLastName())
                        .subClassID(parent.getParentId())
                        .build();
            }
            case ADMIN -> {
                return UserResponse.builder()
                        .status("Success")
                        .message("Successfully returning user details for admin")
                        .userID(user.getUserId())
                        .email(user.getEmail())
                        .pesel(user.getPesel())
                        .role(user.getRole())
                        .firstName("ADMIN")
                        .lastName("ADMIN")
                        .subClassID(-1L)
                        .build();
            }
        }

        return UserResponse.builder()
                .userID(user.getUserId())
                .email(user.getEmail())
                .pesel(user.getPesel())
                .role(user.getRole())
                .firstName("ADMIN")
                .lastName("ADMIN")
                .subClassID(-1L)
                .build();
    }
}
