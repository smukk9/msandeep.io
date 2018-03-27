package io.sandeep.blog.service;

import com.fasterxml.jackson.databind.JsonNode;
import io.sandeep.blog.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {
    List<Tag> getAllTags();
    Optional<Tag> getTagById(int id);
    boolean deleteTagById(int id);
    Tag save(Tag tag);
    boolean saveJsonTag(JsonNode actualObj);
    public boolean createTag(String tag);
}
