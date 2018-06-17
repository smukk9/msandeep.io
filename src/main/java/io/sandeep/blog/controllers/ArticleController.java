package io.sandeep.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/{id}")
    public String showArticle(ModelMap map){

        return "/article/article";
    }

}
