package io.sandeep.blog.resource;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.sandeep.blog.BlogException.BlogGenericError;
import io.sandeep.blog.entity.Article;
import io.sandeep.blog.entity.Tag;
import io.sandeep.blog.enums.JsonKeys;
import io.sandeep.blog.repository.ArticleRepository;
import io.sandeep.blog.service.ArticleService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleResources {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ArticleService articleService;

    //Remove this
    @Autowired
    private ArticleRepository articleRepository;

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
        String content=firstNode.get(JsonKeys.CONTENT.getValue()).toString();

        boolean checkHTML = Jsoup.isValid(content, Whitelist.basic());

          Article saveArticle = articleService.saveJsonArticle(firstNode);
        if (checkHTML){

            logger.info("valid html: {}", content);
        }else
        {
            Document dirtyDoc=Jsoup.parse(content);
            Document cl = new Cleaner(Whitelist.basic()).clean(dirtyDoc);
            logger.info("cleaned html: {}",cl );
        }
        return ResponseEntity.ok().body(content);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getArticleBy(@RequestParam("getBy") int searchVal ){

            //Search value.
        //String dateobj = searchVal.substring(1,searchVal.length()-1)+"-"+"01"+"-"+"01";
        logger.info("Check the object: {}", searchVal);

      //  LocalDate ld = LocalDate.parse(dateobj);
                List<Article> byYearList = articleRepository.findByCreatedateEndsWith(searchVal);
                logger.info("yearby list: {}", byYearList);
                return ResponseEntity.ok().body(byYearList);


    }

    @GetMapping("/archives")
    public  ResponseEntity<?> getArchives() throws JsonProcessingException {

      ArrayNode archives = articleService.getarchives();

        if(archives.size()>0){

            return ResponseEntity.ok(archives);

        }else{

            return ResponseEntity.notFound().build();

        }


    }

    @GetMapping("/latest")
    public  ResponseEntity<?> getLastestArticle (){

        List<Article> articles = articleService.getLastestPageable();
        return  ResponseEntity.ok(articles);
    }

    @PutMapping
    public ResponseEntity<?> updateArticle(@RequestBody String article) throws IOException {


        logger.info("Request posted from update page: {}", article);
        ObjectMapper mapper = new ObjectMapper();


        JsonNode actualObj = mapper.readTree(article);
        JsonNode firstNode = actualObj.get(0);
        String content=firstNode.get(JsonKeys.CONTENT.getValue()).toString();
        logger.info("Recevied the udpate Json: {}", firstNode);
        boolean checkHTML = Jsoup.isValid(content, Whitelist.basic());

        Article saveArticle = articleService.UpdateJsonArticle(firstNode);
        if (checkHTML){

            logger.info("valid html for update: {}", content);
        }else
        {
            Document dirtyDoc=Jsoup.parse(content);
            Document cl = new Cleaner(Whitelist.basic()).clean(dirtyDoc);
            logger.info("cleaned html: {}",cl );
        }
        return ResponseEntity.ok().body(content);
    }

}



