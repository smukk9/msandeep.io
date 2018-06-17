package io.sandeep.blog.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sandeep.blog.BlogException.BlogGenericError;
import io.sandeep.blog.entity.Article;
import io.sandeep.blog.entity.Tag;
import io.sandeep.blog.repository.ArticleRepository;
import io.sandeep.blog.repository.TagRepository;
import io.sandeep.blog.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/tag")
public class TagResources  {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TagService tagService;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public ResponseEntity<List<Tag>> listAllTags(){

        logger.info("Request all tags: {}");
        return ResponseEntity.status(HttpStatus.OK).body(tagService.getAllTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTagById(@PathVariable int id){

        logger.info("Request tag with id: {}",id );
        return tagService.getTagById(id).<ResponseEntity<?>> map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new BlogGenericError("Article not found with id :"+id)));

    }

    @PostMapping
    public ResponseEntity<?> saveTag(@RequestBody String tag) throws IOException{

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(tag);
        logger.info("****Tag receved from ajax****: {}", tag);
        boolean savedTag = tagService.saveJsonTag(actualObj);

        return ResponseEntity.ok().body(savedTag);
    }

    //tag?tagName="sandee"
    //TO-DO:
    // -tagname validation
    @GetMapping("/search")
    public ResponseEntity<?> searchApi(@RequestParam String tagName){

        logger.info("log the value from the ?: {}", tagName);
        //call the searchTag in tagimpl class

       Optional<List<Tag>> tagList = tagService.searchTagsByName(tagName);
        List<String> stringName = new ArrayList<>();

        if(tagList.isPresent()){

            logger.info("is from the inside if: {}", tagList );

            return ResponseEntity.ok().body(tagList.get());
        }else
        {
            logger.info("No value found for the reqeust" );
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/article/count")
    public ResponseEntity<?> getArticleByTags(){

     //   List test = tagRepository.findyArticleByTag();

        List<Article> alltags = articleRepository.findAll();




        return ResponseEntity.ok().body(tagService.tagCount());
    }


}
