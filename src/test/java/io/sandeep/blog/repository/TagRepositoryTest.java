package io.sandeep.blog.repository;

import io.sandeep.blog.entity.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TagRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TagRepository tagRepository;


    @Test
    public void existsByTagName() throws Exception {

        testEntityManager.persist(Tag.builder().tagName("sandeep").build());
        boolean check = tagRepository.existsByTagName("sandeep");
        assertTrue(check);

    }

}