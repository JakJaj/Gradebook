package com.jj.Gradebook.controller.users;

import com.jj.Gradebook.controller.response.users.UserResponse;
import com.jj.Gradebook.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @GetMapping()
    @CrossOrigin
    public ResponseEntity<UserResponse> getUserDetails(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usersService.getUserDetails(token));
    }
}
