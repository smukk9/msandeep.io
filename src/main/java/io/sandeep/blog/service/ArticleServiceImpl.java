package io.sandeep.blog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.sandeep.blog.configuration.AuthenticationFacade;
import io.sandeep.blog.entity.Article;
import io.sandeep.blog.entity.Tag;
import io.sandeep.blog.entity.User;
import io.sandeep.blog.enums.JsonKeys;
import io.sandeep.blog.repository.ArticleRepository;
import io.sandeep.blog.repository.TagRepository;
import io.sandeep.blog.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
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

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Article> getAllArticles() {

        logger.info("Get all articles from DB");
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> getArticleById(int id) {

        Optional<Article> article = articleRepository.findById(id);
        logger.info("Requested article : {}", article);
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

    public Article saveJsonArticle(JsonNode article) {

        Authentication authentication = authenticationFacade.getAuthentication();
        Optional<String> username = Optional.ofNullable(authentication.getName());

        User user;
        if (!username.isPresent()) {
            logger.info("checking  the if:{}", username.isPresent());
            user = userRepository.findByUsername(username.get()).get();
        } else {
            logger.info("checking else the if");
            user = userRepository.findByUsername("smukk9").get();
        }


        JsonNode tagCount = article.get(JsonKeys.TAGS.getValue());
        logger.info("JsonNode from the request: {}", tagCount);
        List<Tag> tagSet = new LinkedList<>();
        if (tagCount.isArray()) {
            for (final JsonNode objNode : tagCount) {

                Optional<Tag> tag = tagRepository.findById(objNode.asInt());
                logger.info("Retrieved tag from DB: {}", tag);
                tag.ifPresent(tagSet::add);
            }
        } else {
            Optional<Tag> tag = tagRepository.findById(tagCount.get("id").asInt());
            logger.info("Retrieved tag from DB: {}", tag);
            tag.ifPresent(tagSet::add);
        }


        Article newArticle = Article.builder()
                .author(user)
                .title(article.get(JsonKeys.TITLE.getValue()).toString())
                .content(article.get(JsonKeys.CONTENT.getValue()).toString())
                .tags(tagSet)
                .build();

        logger.info("Saved Article is: {}", newArticle);
        return articleRepository.save(newArticle);

    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public ArrayNode getarchives() throws JsonProcessingException {

        //get all articles
        List<Article> allArts = articleRepository.findAll();
        logger.info("all articles for archives: {}", allArts);

        ArrayNode arrayNode = mapper.createArrayNode();
        HashMap<Integer, List<Article>> archives = new HashMap<>();

        for (Article article : allArts) {

            //get Year of the article
            Date fullDate = article.getCreateDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fullDate);
            int year = calendar.get(Calendar.YEAR);

            if (archives.containsKey(year)) {

                //get the list
                List<Article> key_value = archives.get(year);
                key_value.add(article);
            } else {
                List<Article> article_value = new LinkedList<>();
                boolean check = article_value.add(article);
                if (check) {
                    archives.put(year, article_value);
                } else {
                    logger.error("failed to add article: {}", article.getId());
                }
            }

        }

        //i dont like this, but for now lets get with it. will find a better way to get it.

        //get string json
        //    String JsonResponse = mapper.writeValueAsString(archives);


        Iterator it = archives.entrySet().iterator();
        while (it.hasNext()) {

            ObjectNode archiveNode = mapper.createObjectNode();
            Map.Entry pair = (Map.Entry) it.next();
            int year_key = (Integer) (pair.getKey());
            logger.info("Extract of the key from hasmap: {}", year_key);
            String JsonResponse = mapper.writeValueAsString(pair.getValue());
            archiveNode.put("year", year_key);
            archiveNode.put("articleArray", JsonResponse);
            arrayNode.add(archiveNode);
        }

        return arrayNode;

    }

    //get the lates article
    @Override
    public List<Article> getLastestPageable() {

        Pageable pageable = new PageRequest(0, 6, Sort.Direction.ASC, "createDate");
        Page<Article> topArticle = articleRepository.findAll(pageable);
        return topArticle.getContent();
    }

    public Article UpdateJsonArticle(JsonNode article) {

        //Update : get user

        Authentication authentication = authenticationFacade.getAuthentication();
        Optional<String> username = Optional.ofNullable(authentication.getName());

        User user;
        if (!username.isPresent()) {
            logger.info("checking  update json  if:{}", username.isPresent());
            user = userRepository.findByUsername(username.get()).get();
        } else {
            logger.info("checking else the if");
            user = userRepository.findByUsername("smukk9").get();
        }

        int id = article.get("id").asInt();
        Optional<Article> updateArticle = articleRepository.findById(id);
        JsonNode tagCount = article.get(JsonKeys.TAGS.getValue());
        logger.info("JsonNode update request the request: {}", tagCount);
        List<Tag> tagSet = new LinkedList<>();
        // List<Tag> existingTags = updateArticle.get().getTags();
        if (tagCount.isArray()) {
            for (final JsonNode objNode : tagCount) {


                String cleanTag = objNode.toString().substring(1, objNode.toString().length() - 1);
                logger.info("Tag not present checking for arraay condit, updating the tags:{}", cleanTag);
                Tag tag = tagRepository.findByTagName(cleanTag);

                logger.info("Retrived tag: {}", tag);
                tagSet.add(tag);


            }
        } else {

            String putTag = tagCount.get(0).toString().substring(1, tagCount.get(0).toString().length() - 1);
            logger.info("Tag not present, updating the tags:{}", putTag);
            Tag tag = tagRepository.findByTagName(putTag);


            tagSet.add(tag);

        }

        updateArticle.get().setAuthor(user);
        updateArticle.get().setTags(tagSet);
        updateArticle.get().setContent(article.get(JsonKeys.CONTENT.getValue()).toString());
        updateArticle.get().setTitle(article.get(JsonKeys.TITLE.getValue()).toString());

//        Article udpateArticle = updateArticle.builder()
//                .author(user)
//                .title(article.get(JsonKeys.TITLE.getValue()).toString())
//                .content(article.get(JsonKeys.CONTENT.getValue()).toString())
//                .tags(tagSet)
//                .build();

        logger.info("Update Article : {}", updateArticle.get());
        return articleRepository.save(updateArticle.get());

    }
}
