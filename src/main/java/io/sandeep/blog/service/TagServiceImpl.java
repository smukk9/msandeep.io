package io.sandeep.blog.service;

import com.fasterxml.jackson.databind.JsonNode;
import io.sandeep.blog.entity.Tag;
import io.sandeep.blog.enums.JsonKeys;
import io.sandeep.blog.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TagRepository tagRepository;






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

}