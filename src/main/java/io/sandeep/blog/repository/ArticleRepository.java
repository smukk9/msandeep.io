package io.sandeep.blog.repository;

import io.sandeep.blog.entity.Article;
import io.sandeep.blog.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Optional<Article> findById(int id);
    List<Article> findAll();
    void deleteById(int id);
    Article save(Article article);

    @Query("select u from Article u where year(u.createDate) like ?1")
    List<Article> findByCreatedateEndsWith(int year);

   // @Query("select a from Article a where u.tag like ?1")
    List<Article>findByTagsIn(List<Tag> tags);

    //get the latest top article
    Page<Article> findBycreateDate(Pageable pageable);
}
