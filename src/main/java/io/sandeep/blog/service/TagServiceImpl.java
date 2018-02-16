package io.sandeep.blog.service;

import com.fasterxml.jackson.databind.JsonNode;
import io.sandeep.blog.entity.Tag;
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

        JsonNode tagName = actualObj.get("tagnames");
        logger.info("JsonNode from the request: {}", tagName);

        if(tagName.isArray()){
            for (final  JsonNode objNode: tagName){
                Tag saveTag = Tag.builder().tagName(objNode.get("name").textValue()).build();
                Tag tag = tagRepository.save(saveTag);
                logger.info("Saving Tag from request: {}", tag.toString());
            }
            return true;
        }else{
                Tag saveTag = Tag.builder().tagName(tagName.asText()).build();
                Tag tag = tagRepository.save(saveTag);
             logger.info("Saving Tag from request: {}", tag.toString());
            return true;
            }
    }

    @Override
  public  boolean isTagnameExists(String tagName){

        return tagRepository.existsByTagName(tagName);


}
}