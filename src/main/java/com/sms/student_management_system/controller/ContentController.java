package com.sms.student_management_system.controller;

import com.sms.student_management_system.entity.MyAppUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping("login")
    public String login() {
         return "login";
    }

    @GetMapping("/req/signup")
    public String signup(Model model) {
        model.addAttribute("user", new MyAppUser());
        return "signup";
    }
}