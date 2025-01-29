package com.jj.Gradebook.service.auth;

import com.jj.Gradebook.config.JwtService;
import com.jj.Gradebook.controller.auth.SecurityUtils;
import com.jj.Gradebook.controller.request.auth.*;
import com.jj.Gradebook.controller.response.auth.AuthenticationResponse;
import com.jj.Gradebook.dao.*;
import com.jj.Gradebook.entity.*;
import com.jj.Gradebook.exceptions.DateFormatException;
import com.jj.Gradebook.exceptions.EntityAlreadyExistsException;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.Class;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private ParentRepository parentRepository;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private ClassRepository classRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    private SimpleDateFormat dateFormat;

    @BeforeEach
    void setUp() {
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    }

    @Test
    void authenticate_shouldReturnSuccess() {
        AuthenticationRequest request = mock(AuthenticationRequest.class);
        User user = mock(User.class);
        String token = "jwt_token";

        when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn(token);

        AuthenticationResponse response = authenticationService.authenticate(request);

        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals("Successful login", response.getMessage());
        assertEquals(token, response.getToken());
    }

}
