package com.sms.student_management_system.controller;

import com.sms.student_management_system.entity.MyAppUser;
import com.sms.student_management_system.exception.UserIsAlreadyPresentException;
import com.sms.student_management_system.repository.MyAppUserRepository;
import com.sms.student_management_system.service.MyAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private MyAppUserRepository repository;

    @Autowired
    private MyAppUserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/req/signup")
    public String createUser(@ModelAttribute("user") MyAppUser user, Model model) {
        try{
            MyAppUser userTmp = service.signUp(user);
            userTmp.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.save(userTmp);
            return "redirect:/login";
        } catch (UserIsAlreadyPresentException e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("user", user);
            return "signup";
        }
    }
}
