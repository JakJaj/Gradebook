package com.jj.Gradebook.controller.Auth;

import com.jj.Gradebook.controller.request.*;
import com.jj.Gradebook.controller.response.AuthenticationResponse;
import com.jj.Gradebook.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register/students")
    public ResponseEntity<AuthenticationResponse> registerStudent(@RequestBody RegisterStudentRequest request){
        return ResponseEntity.ok(authenticationService.registerStudent(request));
    }

    @PostMapping("/register/teachers")
    public ResponseEntity <AuthenticationResponse> registerTeacher(@RequestBody RegisterTeacherRequest request){
        return ResponseEntity.ok(authenticationService.registerTeacher(request));
    }

    @PostMapping("/register/parents")
    public ResponseEntity<AuthenticationResponse> registerParent(@RequestBody RegisterParentRequest request){
        return ResponseEntity.ok(authenticationService.registerParent(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}