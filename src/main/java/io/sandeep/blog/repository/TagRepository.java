package io.sandeep.blog.repository;

import io.sandeep.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

     List<Tag> findAll();
     Tag save(Tag tag);
     Optional<Tag> findById(int id);
     List<Tag> findByTagName(String tagName);
     boolean existsByTagNameIgnoreCase(String tagName);

    @Query(
            value = "SELECT * FROM tag WHERE UPPER(tagname) LIKE %:searchTerm%",
            nativeQuery = true
    )
    Optional<List<Tag>> searchWithNativeQuery(@Param("searchTerm") String searchTerm);


}
