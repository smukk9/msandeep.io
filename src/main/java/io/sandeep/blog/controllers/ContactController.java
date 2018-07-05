package io.sandeep.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/me")
public class ContactController {

    @GetMapping
    public String viewMe(ModelMap  Map){return "me/me";}
}
