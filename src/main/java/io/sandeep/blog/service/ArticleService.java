package io.sandeep.blog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.sandeep.blog.entity.Article;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

     List<Article> getAllArticles();
     Optional<Article> getArticleById(int id);
     boolean deleteArticleById(int id);
     Article save(Article article);
     Article saveJsonArticle(JsonNode actualObj);
     ArrayNode  getarchives() throws JsonProcessingException;
     List<Article> getLastestPageable();
}
