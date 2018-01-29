package io.sandeep.blog.configuration;

import io.sandeep.blog.entity.Role;
import io.sandeep.blog.entity.User;
import io.sandeep.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/*
 *
 * class used to save the init user for working.
 * Creates and admin with username sandeep and password
 */

@Component
public class BlogApplicationRunner implements ApplicationRunner  {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        logger.info("Runner*************:");

        Set<Role> userRoles = new HashSet<>();
        Role role =  Role.builder().role("ROLE_ADMIN").build();
        userRoles.add(role);
        User user =  User.builder().username("smukk9").email("smukk9@gmail.com").password("sandeep").roles(userRoles).active(1).build();
        userService.save(user);
        logger.info("***********User setup complete**********");
        logger.info(": User that is saved{}", user);


    }
}
