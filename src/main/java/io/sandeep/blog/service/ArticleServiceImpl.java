package io.sandeep.blog.service;

import io.sandeep.blog.entity.Article;
import io.sandeep.blog.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getAllArticles() {

        logger.info("Get all articles from DB");
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> getArticleById(int id) {

            return articleRepository.findById(id);

    }

    @Override
    public boolean deleteArticleById(int id) {


        return true;
    }

    @Override
    public Article save(Article article) {

       return  articleRepository.save(article);


    }
}
