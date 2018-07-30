package io.sandeep.blog.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.sandeep.blog.entity.Article;
import io.sandeep.blog.entity.Tag;
import io.sandeep.blog.enums.JsonKeys;
import io.sandeep.blog.repository.ArticleRepository;
import io.sandeep.blog.repository.TagRepository;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ArticleRepository articleRepository;





    @Override
    public List<Tag> getAllTags() {

        logger.info("Requested all Tags" );
        return tagRepository.findAll();

    }

    @Override
    public Optional<Tag> getTagById(int id) {

        Optional<Tag> tag= tagRepository.findById(id);
        logger.info("Requested article : {}", tag );
        return tag;

    }

    @Override
    public boolean deleteTagById(int id) {
        return false;
    }

    @Override
    public Tag save(Tag tag) {
        return null;
    }

    /*
     * separate the save array and save a single tag method in to two
     *
     *
     */
    @Override
    public boolean saveJsonTag(JsonNode actualObj) {

        JsonNode tagName = actualObj.get(JsonKeys.TAGNAME.getValue());

        logger.info("JsonNode from the request: {}", tagName);
        logger.info("checking if is array: {}", tagName.isArray() );




        if(tagName.isArray()){
            for (final  JsonNode objNode: tagName){
                //Replace "" first and last on the tag
                String tag= objNode.get("name").toString().replace("\"","");

                //Check if tag exists
                boolean check= tagRepository.existsByTagNameIgnoreCase(tag);
                logger.info("Does tag exists ? : {}", check);

                /*
                check if True -> skip creation
                         False -> Create tag
                 */
                if(!check){
                    logger.info(" saving tag, creating tag: {}"  , tag );
                    createTag(tag);
                }else {
                    logger.info("Not saving tag, exists skipping: {}"  , tag );
                }
            }
        }
        return  true;
    }


    @Override
    public boolean createTag(String tag){

    Tag saveTag = Tag.builder().tagName(tag).build();
    Tag newTag = tagRepository.save(saveTag);
    logger.info("Saving Tag from request: {}", newTag);
    return true;
    }

    public Optional<List<Tag>> searchTagsByName(String tagName){

        //find all the tagName that matach the string

        String upperTag = tagName.toUpperCase();
       Optional<List<Tag>> tagList = tagRepository.searchWithNativeQuery(upperTag);
        return  tagList;
    }


    //find all article and loop through each one of them
    //count the tag and
        //insert if not exisits else increse
    @Override
    public ArrayNode tagCount(){

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

       // ObjectNode objectNode = mapper.createObjectNode();


        //Return a hasmap
        HashMap<Tag, Integer> tagCountMap = new HashMap<>();

        //get all article
        List<Article> allArts = articleRepository.findAll();

        int initcount=1;
        int incrementCount=0;
        for(Article article : allArts){

            List<Tag> tagsList = article.getTags();
            for (Tag tag : tagsList) {

                if(!tagCountMap.containsKey(tag)){
                    //add value
                    tagCountMap.put(tag,initcount);

                }else{

                    int tagValue = tagCountMap.get(tag);
                    //increment the count
                    tagValue++;
                    //put the value back.
                    tagCountMap.put(tag, tagValue);
                }
            }
        }

        Iterator it = tagCountMap.entrySet().iterator();
        while (it.hasNext()){
            ObjectNode tagCountNode = mapper.createObjectNode();
            Map.Entry pair = (Map.Entry)it.next();
            Tag tag = (Tag)pair.getKey();
            logger.info("Extract of tagCountMap: {}",tag );

            tagCountNode.put("tagName", tag.getTagName());
            tagCountNode.put("tagId", tag.getId());
            tagCountNode.put("tagCount", (Integer) pair.getValue());
            arrayNode.add(tagCountNode);

        }

        return arrayNode;

    }

    @Override
    public List<Article> getArticleByTagId(int id) {

        List<Article> allArticles = articleRepository.findAll();
        Tag tag = tagRepository.getOne(id);
        List<Article> responseArts=new ArrayList<>();

        for(Article article : allArticles){

            List<Tag> tags = article.getTags();

            if(tags.contains(tag)){

                logger.info("the tag is available: {}",tag );
                responseArts.add(article);
            }else{

                logger.info("Tag not available : {}", tag );
            }



        }

        return responseArts;
    }


}