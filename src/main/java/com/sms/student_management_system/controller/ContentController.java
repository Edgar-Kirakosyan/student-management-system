package com.sms.student_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping("login")
    public String login() {
         return "login";
    }

    @GetMapping("/req/signup")
    public String signup() {
        return "signup";
    }
}
