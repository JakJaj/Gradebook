package com.jj.Gradebook.service.auth;



import com.jj.Gradebook.config.JwtService;
import com.jj.Gradebook.controller.Auth.SecurityUtils;
import com.jj.Gradebook.controller.request.AuthenticationRequest;
import com.jj.Gradebook.controller.request.RegisterRequest;
import com.jj.Gradebook.controller.response.AuthenticationResponse;
import com.jj.Gradebook.dto.UserRepository;
import com.jj.Gradebook.entity.User;
import com.jj.Gradebook.exceptions.NoSuchUserException;
import com.jj.Gradebook.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
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

        repository.save(user);

        //TODO: TWORZ USER /TEACHER / PARENT W ZALEZNOSCI OD ROLI

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .status("Success")
                .message("Successful registration")
                .token(jwtToken)
                .build();
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

