package com.jj.Gradebook.service.auth;



import com.jj.Gradebook.config.JwtService;
import com.jj.Gradebook.controller.auth.SecurityUtils;
import com.jj.Gradebook.controller.request.auth.*;
import com.jj.Gradebook.controller.response.auth.AuthenticationResponse;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.entity.Class;
import com.jj.Gradebook.entity.Parent;
import com.jj.Gradebook.entity.Student;
import com.jj.Gradebook.entity.Teacher;
import com.jj.Gradebook.entity.User;
import com.jj.Gradebook.exceptions.DateFormatException;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import com.jj.Gradebook.exceptions.EntityAlreadyExistsException;
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
    private final ClassRepository classRepository;
    private final static int PASSWORD_LENGTH = 16;

    public AuthenticationResponse registerStudent(RegisterStudentRequest request){

        String generatedPassword = SecurityUtils.generateSalt(PASSWORD_LENGTH);


        Class theClass = null;

        if (request.getStudent().getClassID() != null){
            theClass = classRepository.findById(request.getStudent().getClassID()).orElseThrow(() -> new NoSuchEntityException("No class with a specified id"));
        }

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
                    .studentClass(theClass)
                    .city(request.getStudent().getCity())
                    .street(request.getStudent().getStreet())
                    .houseNumber(request.getStudent().getHouseNumber())
                    .user(user)
                    .build();

            Student savedStudent = studentRepository.save(student);

            return AuthenticationResponse.builder()
                    .status("Success")
                    .message("Successfully registered")
                    .token(token)
                    .id(savedStudent.getStudentId())
                    .password(generatedPassword)
                    .build();
        } catch (ParseException e){
            System.out.println("Wrong date format");
            throw new DateFormatException("Wrong date format");
        }

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
            System.out.println(request.getTeacher().getDateOfBirth());
            System.out.println(request.getTeacher().getDateOfEmployment());

            Date dateOfBirth = dateFormat.parse(request.getTeacher().getDateOfBirth());
            Date dateOfEmployment = dateFormat.parse(request.getTeacher().getDateOfEmployment());

            Teacher teacher = Teacher.builder()
                    .firstName(request.getTeacher().getFirstName())
                    .lastName(request.getTeacher().getLastName())
                    .dateOfBirth(dateOfBirth)
                    .dateOfEmployment(dateOfEmployment)
                    .user(user)
                    .build();

            Teacher savedTeacher = teacherRepository.save(teacher);

            return AuthenticationResponse.builder()
                    .status("Success")
                    .message("Successfully registered")
                    .token(token)
                    .id(savedTeacher.getTeacherId())
                    .password(generatedPassword)
                    .build();
        } catch (ParseException e){
            System.out.println("Wrong date format");
            throw new DateFormatException("Wrong date format");
        }
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

        Parent savedParent = parentRepository.save(parent);

        return AuthenticationResponse.builder()
                .status("Success")
                .message("Successfully registered")
                .token(token)
                .id(savedParent.getParentId())
                .password(generatedPassword)
                .build();
    }

    public AuthenticationResponse registerAdmin(RegisterRequest request) {

        String generatedPassword = SecurityUtils.generateSalt(PASSWORD_LENGTH);

        User user = registerUser(RegisterRequest.builder()
                .password(generatedPassword)
                .email(request.getEmail())
                .pesel(request.getPesel())
                .role(request.getRole())
                .build());

        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .status("Success")
                .message("Successfully registered")
                .token(token)
                .password(generatedPassword)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){

        var user = repository.findByEmail(request.getEmail());
        if (user.isEmpty()) throw new NoSuchEntityException("No user with a specified credentials");


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
                .role(String.valueOf(user.get().getRole()))
                .build();
    }

    private User registerUser(RegisterRequest request){
        if (repository.findByPesel(request.getPesel()).isPresent()) throw new EntityAlreadyExistsException("User with this pesel already exists");

        var existingUser = repository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) throw new EntityAlreadyExistsException("User with this email already exists");
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
}

