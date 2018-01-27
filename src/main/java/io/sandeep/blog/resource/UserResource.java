package io.sandeep.blog.resource;

import io.sandeep.blog.entity.Role;
import io.sandeep.blog.entity.User;
import io.sandeep.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RequestMapping("/rest")
@RestController
public class UserResource {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String welcome (){

        return "You are admin that isw why you are seeing this";
    }


    @GetMapping("/all")
    public  User hello(){

    Set<Role> userRoles = new HashSet<>();
    Role role =  Role.builder().role("ROLE_ADMIN").build();
    userRoles.add(role);
    User user =  User.builder().username("smukk9").email("smukk9@gmail.com").password("sandeep").roles(userRoles).active(1).build();
    userService.save(user);
     return  user;

    }
}
