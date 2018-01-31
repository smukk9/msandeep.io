package io.sandeep.blog.resource;

import io.sandeep.blog.BlogException.BlogGenericError;
import io.sandeep.blog.entity.Article;
import io.sandeep.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleResources {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<Article> listAllArticle(){

        return articleService.getAllArticles();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getArticleById(@PathVariable int id){

        return articleService.getArticleById(id).<ResponseEntity<?>> map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BlogGenericError("Article not found with id :"+id)));
    }




}
