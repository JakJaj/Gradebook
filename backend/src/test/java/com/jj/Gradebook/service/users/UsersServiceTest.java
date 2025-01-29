package com.jj.Gradebook.service.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.jj.Gradebook.config.JwtService;
import com.jj.Gradebook.controller.response.users.UserResponse;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.entity.*;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import com.jj.Gradebook.service.users.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class UsersServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private ParentRepository parentRepository;

    @InjectMocks
    private UsersService usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserDetails_returnsStudentDetails() {
        String token = "Bearer token";
        String email = "student@example.com";
        User user = new User();
        user.setUserId(1L);
        user.setEmail(email);
        user.setRole(Role.STUDENT);
        Student student = new Student();
        student.setStudentId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setUser(user);

        when(jwtService.extractUsername(token.substring(7))).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(studentRepository.findByUser_UserId(1L)).thenReturn(Optional.of(student));

        UserResponse response = usersService.getUserDetails(token);

        assertEquals("Success", response.getStatus());
        assertEquals("Successfully returning user details for student", response.getMessage());
        assertEquals(1L, response.getUserID());
        assertEquals(email, response.getEmail());
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
    }

    @Test
    void getUserDetails_returnsTeacherDetails() {
        String token = "Bearer token";
        String email = "teacher@example.com";
        User user = new User();
        user.setUserId(1L);
        user.setEmail(email);
        user.setRole(Role.TEACHER);
        Teacher teacher = new Teacher();
        teacher.setTeacherId(1L);
        teacher.setFirstName("Jane");
        teacher.setLastName("Doe");
        teacher.setUser(user);

        when(jwtService.extractUsername(token.substring(7))).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(teacherRepository.findByUser_UserId(1L)).thenReturn(Optional.of(teacher));

        UserResponse response = usersService.getUserDetails(token);

        assertEquals("Success", response.getStatus());
        assertEquals("Successfully returning user details for teacher", response.getMessage());
        assertEquals(1L, response.getUserID());
        assertEquals(email, response.getEmail());
        assertEquals("Jane", response.getFirstName());
        assertEquals("Doe", response.getLastName());
    }

    @Test
    void getUserDetails_returnsParentDetails() {
        String token = "Bearer token";
        String email = "parent@example.com";
        User user = new User();
        user.setUserId(1L);
        user.setEmail(email);
        user.setRole(Role.PARENT);
        Parent parent = new Parent();
        parent.setParentId(1L);
        parent.setFirstName("Jim");
        parent.setLastName("Beam");
        parent.setUser(user);

        when(jwtService.extractUsername(token.substring(7))).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(parentRepository.findByUser_UserId(1L)).thenReturn(Optional.of(parent));

        UserResponse response = usersService.getUserDetails(token);

        assertEquals("Success", response.getStatus());
        assertEquals("Successfully returning user details for parent", response.getMessage());
        assertEquals(1L, response.getUserID());
        assertEquals(email, response.getEmail());
        assertEquals("Jim", response.getFirstName());
        assertEquals("Beam", response.getLastName());
    }

    @Test
    void getUserDetails_returnsAdminDetails() {
        String token = "Bearer token";
        String email = "admin@example.com";
        User user = new User();
        user.setUserId(1L);
        user.setEmail(email);
        user.setRole(Role.ADMIN);

        when(jwtService.extractUsername(token.substring(7))).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserResponse response = usersService.getUserDetails(token);

        assertEquals("Success", response.getStatus());
        assertEquals("Successfully returning user details for admin", response.getMessage());
        assertEquals(1L, response.getUserID());
        assertEquals(email, response.getEmail());
        assertEquals("ADMIN", response.getFirstName());
        assertEquals("ADMIN", response.getLastName());
    }

    @Test
    void getUserDetails_throwsExceptionForInvalidToken() {
        String token = "Bearer invalidToken";

        when(jwtService.extractUsername(token.substring(7))).thenThrow(new NoSuchEntityException("Invalid token"));

        NoSuchEntityException exception = assertThrows(NoSuchEntityException.class, () -> usersService.getUserDetails(token));

        assertEquals("Invalid token", exception.getMessage());
    }

    @Test
    void getUserDetails_throwsExceptionForNonExistentUser() {
        String token = "Bearer token";
        String email = "nonexistent@example.com";

        when(jwtService.extractUsername(token.substring(7))).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        NoSuchEntityException exception = assertThrows(NoSuchEntityException.class, () -> usersService.getUserDetails(token));

        assertEquals("Can't happen tbh", exception.getMessage());
    }
}