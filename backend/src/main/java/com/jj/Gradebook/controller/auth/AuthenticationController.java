package com.jj.Gradebook.controller.auth;

import com.jj.Gradebook.controller.request.auth.*;
import com.jj.Gradebook.controller.response.auth.AuthenticationResponse;
import com.jj.Gradebook.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.registerAdmin(request));
    }
    @PostMapping("/register/students")
    @CrossOrigin
    public ResponseEntity<AuthenticationResponse> registerStudent(@RequestBody RegisterStudentRequest request){
        return ResponseEntity.ok(authenticationService.registerStudent(request));
    }

    @PostMapping("/register/teachers")
    @CrossOrigin
    public ResponseEntity <AuthenticationResponse> registerTeacher(@RequestBody RegisterTeacherRequest request){
        return ResponseEntity.ok(authenticationService.registerTeacher(request));
    }

    @PostMapping("/register/parents")
    @CrossOrigin
    public ResponseEntity<AuthenticationResponse> registerParent(@RequestBody RegisterParentRequest request){
        return ResponseEntity.ok(authenticationService.registerParent(request));
    }
    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}