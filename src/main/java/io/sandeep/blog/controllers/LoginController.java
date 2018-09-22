package io.sandeep.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

    @RequestMapping("/")
    public String index(ModelMap map) {
        return "index";
    }

    @RequestMapping("/article")
    public String article(ModelMap map){ return "article/article";}


    @RequestMapping("/admin")
    public String admin(ModelMap map){ return "admin/admin";}

    @RequestMapping("/admin/editor")
    public String editor(ModelMap map){ return "admin/editor";}



    @RequestMapping("/archive")
    public String archive(ModelMap map){ return "archive/archive";}

    @RequestMapping("/create/article")
    public ModelAndView createArticleView(){
    ModelAndView modelAndView = new ModelAndView("admin/editor");
    modelAndView.addObject("isArticleTrigger","true");

    return modelAndView;
    }

    @RequestMapping("/admin/markdown")
    public String markdown(ModelMap map){ return "admin/markdown";}
}

