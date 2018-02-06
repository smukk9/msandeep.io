package io.sandeep.blog.resource;


import io.sandeep.blog.configuration.AuthenticationFacade;
import io.sandeep.blog.entity.User;
import io.sandeep.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequestMapping("/api/v1/users")
@RestController
public class UserResource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<User> listAllUsers (){

        Authentication authentication = authenticationFacade.getAuthentication();
        String username =authentication.getName();
        logger.info("Requested to list all users by : {}", username);
        List<User> userList = userService.getAllUsers();
        logger.debug("All user returned:{}", userList);
        return userList;
    }


    @GetMapping("/{id}")
    public  String hello(@PathVariable int id){

        logger.info(" Requested for user with Id : {}", id);
        return "willl send you single user";


    }


}
