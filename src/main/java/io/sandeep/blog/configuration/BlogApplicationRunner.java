package io.sandeep.blog.configuration;

import io.sandeep.blog.entity.Article;
import io.sandeep.blog.entity.Role;
import io.sandeep.blog.entity.Tag;
import io.sandeep.blog.entity.User;
import io.sandeep.blog.repository.TagRepository;
import io.sandeep.blog.service.ArticleService;
import io.sandeep.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


import java.util.*;

/*
 *
 * class used to save the init user for working.
 * Creates and admin with username sandeep and password
 */

@Component
public class BlogApplicationRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private Environment env;


    @Override
    public void run(ApplicationArguments args) throws Exception {


        logger.info("Starting Application Runner");
        String adminName = env.getProperty("ADMIN_USER");
        String adminPassword = env.getProperty("ADMIN_PASSWORD");
        String adminEmail = env.getProperty("ADMIN_EMAIL");

        //Check if username exists in DB
        if (userService.findByUserName(adminName).isPresent()) {
            String email = userService.findByUserName(adminName).get().getEmail();
            logger.info("user exists with username : {}", email);
        } else {
            logger.info("Creating new admin user:{}", adminName);
            Set<Role> userRoles = new HashSet<>();
            Role role = Role.builder().role("ROLE_ADMIN").build();
            userRoles.add(role);
            User user = User.builder().username(adminName).email("ADMIN_EMAIL").password(adminPassword).roles(userRoles).active(1).build();
            userService.save(user);
            logger.info(": User that is saved{}", user);

            logger.info("*****Seeding article data************ ");

            List<Tag> tagList = new LinkedList<>();
            Tag spring = Tag.builder().tagName("Spring").build();
            Tag springboot = Tag.builder().tagName("SpringBoot").build();

            tagList.add(spring);
            tagList.add(springboot);


            tagRepository.save(spring);
            tagRepository.save(springboot);

            Article article1 = Article.builder()
                    .author(user)
                    .title("Learn SpringBoot with me ")
                    .content("Trying learning lorem is the works is the kdiend infn dlldfjjnfdknffnl lkehduda")
                    .tags(tagList)
                    .build();


            logger.info("user with the error: {}", article1);
            Article returnType = articleService.save(article1);

            logger.info("saved article : {}", article1);
            logger.info("Return type: {}", returnType);
            logger.info("*********End Runner***************");

        }
    }
}
