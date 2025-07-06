package com.sms.student_management_system.controller;

import com.sms.student_management_system.entity.MyAppUser;
import com.sms.student_management_system.entity.Role;
import com.sms.student_management_system.repository.MyAppUserRepository;
import org.apache.catalina.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class ContentController {

    private final MyAppUserRepository myAppUserRepository;

    public ContentController(MyAppUserRepository myAppUserRepository) {
        this.myAppUserRepository = myAppUserRepository;
    }

    @GetMapping("login")
    public String login() {
         return "login";
    }

    @GetMapping("/req/signup")
    public String signup(Model model) {
        model.addAttribute("user", new MyAppUser());
        return "signup";
    }

    @GetMapping("/roles")
    public String roleDistribution(@AuthenticationPrincipal UserDetails details) {
        Optional<MyAppUser> optional = myAppUserRepository.findByUsername(details.getUsername());
        MyAppUser user = optional.get();
        Role role = user.getRole();

        if(role.equals(Role.ADMIN) || role.equals(Role.STUDENT)) {
            return "redirect:/students";
        }
        if(role.equals(Role.TEACHER)) {
            return "redirect:/teachers";
        }
        System.out.println(role);
        return "redirect:/404";
    }
}