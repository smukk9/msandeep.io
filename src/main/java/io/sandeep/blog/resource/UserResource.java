package io.sandeep.blog.resource;


import io.sandeep.blog.entity.User;
import io.sandeep.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RequestMapping("/api/v1/users")
@RestController
public class UserResource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<User> listAllUsers (Principal principal){

        String username = principal.getName();

        logger.info("Requested to list all users by : {}", username);


        return userService.getAllUsers();
    }


    @GetMapping("/{id}")
    public  String hello(@PathVariable int id){

        logger.info(" Requested for user with Id : {}", id);
        return "willl send you single user";


    }
}
