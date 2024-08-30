package com.jj.Gradebook.service.user;

import com.jj.Gradebook.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void deleteById(Long id);
}
