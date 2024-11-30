package com.jj.Gradebook.service.auth;



import com.jj.Gradebook.config.JwtService;
import com.jj.Gradebook.controller.auth.SecurityUtils;
import com.jj.Gradebook.controller.request.*;
import com.jj.Gradebook.controller.response.auth.AuthenticationResponse;
import com.jj.Gradebook.dao.ParentRepository;
import com.jj.Gradebook.dao.StudentRepository;
import com.jj.Gradebook.dao.TeacherRepository;
import com.jj.Gradebook.dao.UserRepository;
import com.jj.Gradebook.entity.Parent;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.entity.User;
import com.jj.Gradebook.exceptions.DateFormatException;
import com.jj.Gradebook.exceptions.NoSuchUserException;
import com.jj.Gradebook.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final static int PASSWORD_LENGTH = 10;

    //TODO: ZASTANOWIC SIE KIEDY LACZYC UCZNIA Z RODZICEM!!!!
    public AuthenticationResponse registerStudent(RegisterStudentRequest request){

        String generatedPassword = SecurityUtils.generateSalt(PASSWORD_LENGTH);

        User user = registerUser(
                RegisterRequest.builder()
                        .password(generatedPassword)
                        .email(request.getEmail())
                        .pesel(request.getPesel())
                        .role(request.getRole())
                        .build());

        String token = jwtService.generateToken(user);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date date = dateFormat.parse(request.getStudent().getDateOfBirth());
            Student student = Student.builder()
                    .firstName(request.getStudent().getFirstName())
                    .lastName(request.getStudent().getLastName())
                    .dateOfBirth(date)
                    .city(request.getStudent().getCity())
                    .street(request.getStudent().getStreet())
                    .houseNumber(request.getStudent().getHouseNumber())
                    .user(user)
                    .build();

            studentRepository.save(student);

        } catch (ParseException e){
            System.out.println("Wrong date format");
            throw new DateFormatException("Wrong date format");

        }

        return AuthenticationResponse.builder()
                .status("Success")
                .message("Generated password: " + generatedPassword)
                .token(token)
                .build();
    }

    public AuthenticationResponse registerTeacher(RegisterTeacherRequest request){
        String generatedPassword = SecurityUtils.generateSalt(PASSWORD_LENGTH);
        User user = registerUser(
                RegisterRequest.builder()
                        .password(generatedPassword)
                        .email(request.getEmail())
                        .pesel(request.getPesel())
                        .role(request.getRole())
                        .build());

        String token = jwtService.generateToken(user);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date dateOfBirth = dateFormat.parse(request.getTeacher().getDateOfBirth());
            Date dateOfEmployment = dateFormat.parse(request.getTeacher().getDateOfEmployment());

            Teacher teacher = Teacher.builder()
                    .firstName(request.getTeacher().getFirstName())
                    .lastName(request.getTeacher().getLastName())
                    .dateOfBirth(dateOfBirth)
                    .dateOfEmployment(dateOfEmployment)
                    .user(user)
                    .build();

            teacherRepository.save(teacher);

        } catch (ParseException e){
            System.out.println("Wrong date format");
            throw new DateFormatException("Wrong date format");

        }

        return AuthenticationResponse.builder()
                .status("Success")
                .message("Generated password: " + generatedPassword)
                .token(token)
                .build();
    }

    public AuthenticationResponse registerParent(RegisterParentRequest request){
        String generatedPassword = SecurityUtils.generateSalt(PASSWORD_LENGTH);

        User user = registerUser(
                RegisterRequest.builder()
                        .password(generatedPassword)
                        .email(request.getEmail())
                        .pesel(request.getPesel())
                        .role(request.getRole())
                        .build());

        String token = jwtService.generateToken(user);

        Parent parent = Parent.builder()
                .firstName(request.getParent().getFirstName())
                .lastName(request.getParent().getLastName())
                .user(user)
                .build();

        parentRepository.save(parent);

        return AuthenticationResponse.builder()
                .status("Success")
                .message("Generated password: " + generatedPassword)
                .token(token)
                .build();
    }

    private User registerUser(RegisterRequest request){
        if (repository.findByPesel(request.getPesel()).isPresent()) throw new UserAlreadyExistsException("User with this pesel already exists");

        var existingUser = repository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) throw new UserAlreadyExistsException("User with this email already exists");
        String salt = SecurityUtils.generateSalt();
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(salt + request.getPassword() ))
                .pesel(request.getPesel())
                .salt(salt)
                .role(request.getRole())
                .build();

        return repository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){

        var user = repository.findByEmail(request.getEmail());
        if (user.isEmpty()) throw new NoSuchUserException("No user with a specified credentials");


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        user.get().getSalt() + request.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(user.get());
        return AuthenticationResponse.builder()
                .status("Success")
                .message("Successful login")
                .token(jwtToken)
                .build();
    }
}

