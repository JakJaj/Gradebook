package com.jj.Gradebook.service.user;

import com.jj.Gradebook.dao.UserRepository;
import com.jj.Gradebook.entity.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> result = userRepository.findById(id);

        User theUser = null;
        if(result.isPresent()){

            theUser = result.get();
        }
        else{ //TODO:THINK ABOUT A BETTER APPROACH
            throw new RuntimeException("Did not find user id - " + id);
        }

        return theUser;
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
