package io.sandeep.blog.service;

import io.sandeep.blog.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

     List<Article> getAllArticles();
     Optional<Article> getArticleById(int id);
     boolean deleteArticleById(int id);
     Article save(Article article);

}
