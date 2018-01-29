package io.sandeep.blog.service;

import io.sandeep.blog.entity.User;

import java.util.List;

public interface UserService {

     boolean save(User user);

    List<User> getAllUsers();
}
