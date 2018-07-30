package io.sandeep.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tag")
public class TagController {


    @GetMapping
    public String tags(ModelMap map){ return "tag/tag";}


    @GetMapping("{id}/articles")
    public String tags( @PathVariable int id){ return "tag/articleTag";}

}
