package io.sandeep.blog.service;

import io.sandeep.blog.entity.User;
import io.sandeep.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean save(User user) {
        userRepository.save(user);
        return true;
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
