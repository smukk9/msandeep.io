package io.sandeep.blog.service;

import com.fasterxml.jackson.databind.JsonNode;
import io.sandeep.blog.configuration.AuthenticationFacade;
import io.sandeep.blog.entity.Article;
import io.sandeep.blog.entity.Tag;
import io.sandeep.blog.entity.User;
import io.sandeep.blog.repository.ArticleRepository;
import io.sandeep.blog.repository.TagRepository;
import io.sandeep.blog.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Article> getAllArticles() {

        logger.info("Get all articles from DB");
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> getArticleById(int id) {

        Optional<Article> article= articleRepository.findById(id);
        logger.info("Requested article : {}", article );
            return article;
    }

    @Override
    public boolean deleteArticleById(int id) {

        articleRepository.deleteById(id);
        return true;
    }


    /*
        check the jsonNode, validate and save the article .
     */

    public Article saveJsonArticle(JsonNode  article) {

        Authentication authentication = authenticationFacade.getAuthentication();
        Optional<String > username = Optional.ofNullable(authentication.getName());

        User user;
        if(!username.isPresent()){
            logger.info("checking  the if:{}",username.isPresent());
             user = userRepository.findByUsername(username.get());
        }else{
            logger.info("checking else the if");
             user = userRepository.findByUsername("smukk9");
        }


        JsonNode tagCount = article.get("tags");
        logger.info("JsonNode from the request: {}", tagCount);
        List<Tag> tagSet = new LinkedList<>();
        if (tagCount.isArray()) {
            for (final JsonNode objNode : tagCount) {

                Optional<Tag> tag = tagRepository.findById(objNode.get("id").asInt());
                logger.info("Retrieved tag from DB: {}", tag);
                tag.ifPresent(tagSet::add);
            }
        }else
        {
            Optional<Tag> tag = tagRepository.findById(tagCount.get("id").asInt());
            logger.info("Retrieved tag from DB: {}", tag);
            tag.ifPresent(tagSet::add);
        }



        Article newArticle = Article.builder()
                .author(user)
                .title(article.get("title").toString())
                .content(article.get("content").toString())
                .tags(tagSet)
                .build();

        logger.info("Saved Article is: {}", newArticle);
        return  articleRepository.save(newArticle);


    }

    @Override
    public Article save(Article article){
        return  articleRepository.save(article);
    }

}
