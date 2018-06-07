package io.sandeep.blog.resource;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sandeep.blog.BlogException.BlogGenericError;
import io.sandeep.blog.entity.Article;
import io.sandeep.blog.entity.Tag;
import io.sandeep.blog.enums.JsonKeys;
import io.sandeep.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleResources {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<Article> listAllArticle() {

        return articleService.getAllArticles();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getArticleById(@PathVariable int id) {

        logger.info(" Request article Id: {}", id);
        return articleService.getArticleById(id).<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new BlogGenericError("Article not found with id :" + id)));


    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable int id) {

        boolean test = articleService.deleteArticleById(id);

        return "Deleted";
    }

    @PostMapping
    public ResponseEntity<?> saveArticle(@RequestBody String article) throws IOException {


        logger.info("Request posted from editor page: {}", article);
        ObjectMapper mapper = new ObjectMapper();


        JsonNode actualObj = mapper.readTree(article);
        JsonNode firstNode = actualObj.get(0);
        JsonNode tagCount = firstNode.get(JsonKeys.TAGS.getValue());
        Article saveArticle = articleService.saveJsonArticle(firstNode);

        return ResponseEntity.ok().body(saveArticle);


    }
}



