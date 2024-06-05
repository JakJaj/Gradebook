package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.User;
import com.jj.Gradebook.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable int id){
        return userService.findById(id);
    }

    @PostMapping("/users") //TODO:TEST HOW TO INSERT USERS
    public User save(@PathVariable User user){
        return userService.save(user);
    }

    @DeleteMapping("/users/{id}") //FIRST YOU HAVE TO DELETE ELEMENTS WITH KEYS CONNECTED TO THIS USER
    public void delete(@PathVariable int id){
        userService.deleteById(id);
    }
}
