package io.sandeep.blog.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.sandeep.blog.entity.Article;
import io.sandeep.blog.entity.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface TagService {
    List<Tag> getAllTags();
    Optional<Tag> getTagById(int id);
    boolean deleteTagById(int id);
    Tag save(Tag tag);
    boolean saveJsonTag(JsonNode actualObj);
     boolean createTag(String tag);
     Optional<List<Tag>> searchTagsByName(String tagName);

     //get all tag by the counto of articlerefference
     ArrayNode tagCount();
     List<Article> getArticleByTagId(int id);
}
