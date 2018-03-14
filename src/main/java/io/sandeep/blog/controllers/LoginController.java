package io.sandeep.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    @RequestMapping("/")
    public String index(ModelMap map) {
        return "index";
    }

    @RequestMapping("/article")
    public String article(ModelMap map){ return "/article/article";}


    @RequestMapping("/admin")
    public String admin(ModelMap map){ return "/admin/admin";}
}
