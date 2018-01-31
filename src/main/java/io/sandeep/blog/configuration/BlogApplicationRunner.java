package io.sandeep.blog.configuration;

import io.sandeep.blog.entity.Article;
import io.sandeep.blog.entity.Role;
import io.sandeep.blog.entity.Tag;
import io.sandeep.blog.entity.User;
import io.sandeep.blog.service.ArticleService;
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

    @Autowired
    private ArticleService articleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        logger.info("********Runner*************:");

        Set<Role> userRoles = new HashSet<>();
        Role role =  Role.builder().role("ROLE_ADMIN").build();
        userRoles.add(role);
        User user =  User.builder().username("smukk9").email("smukk9@gmail.com").password("sandeep").roles(userRoles).active(1).build();
        userService.save(user);
        logger.info("***********User setup complete**********");
        logger.info(": User that is saved{}", user);

        logger.info("*****Seeding article data************ ");

        Set<Tag> tagList = new HashSet<>();
        Tag spring = Tag.builder().tag("Spring").build();
        Tag springboot = Tag.builder().tag("SpringBoot").build();
        tagList.add(spring);
        tagList.add(springboot);

        Article article1 = Article.builder()
                .author(user)
                .title("Learn SpringBoot")
                .content("Trying learning lorem is the works is the kdiend infn dlldfjjnfdknffnl lkehduda")
                .tags(tagList)
                .build();

        logger.info("user with the error: {}",article1 );
        Article returntype= articleService.save(article1);
        logger.info("saved article : {}", article1);
        logger.info("Return type: {}", returntype);
        logger.info("*********End Runner***************" );

    }
}
