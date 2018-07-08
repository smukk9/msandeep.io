package io.sandeep.blog.controllers;

import io.sandeep.blog.entity.Article;
import io.sandeep.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/editor")
public class EditorController {

    @Autowired
    ArticleService articleService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping("/update/{id}")
    public ModelAndView createArticleView(@PathVariable int id) {

        logger.info(" Request article Id: {}", id);
        Optional<Article> article= articleService.getArticleById(id);


        ModelAndView modelAndView = new ModelAndView("admin/updateeditor");
        modelAndView.addObject("isUpdateTrigger","true");


        return modelAndView;
    }
}
