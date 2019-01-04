package io.sandeep.blog.service;

import io.sandeep.blog.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

     boolean save(User user);

    List<User> getAllUsers();
    Optional<User> findByUserName(String username);

}
