package io.sandeep.blog.repository;

import io.sandeep.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

     List<Tag> findAll();
     Tag save(Tag tag);
     Optional<Tag> findById(int id);
     boolean existsByTagName(String tagName);



}
