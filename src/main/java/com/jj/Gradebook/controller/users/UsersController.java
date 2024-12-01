package com.jj.Gradebook.controller.users;

import com.jj.Gradebook.controller.response.users.UserResponse;
import com.jj.Gradebook.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @GetMapping()
    public ResponseEntity<UserResponse> getUserDetails(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usersService.getUserDetails(token));
    }
}
