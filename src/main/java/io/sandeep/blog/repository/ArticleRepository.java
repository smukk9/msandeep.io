package io.sandeep.blog.repository;

import io.sandeep.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>{

    Optional<Article> findById(int id);
    List<Article> findAll();


}
