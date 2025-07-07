package com.sms.student_management_system.controller;

import com.sms.student_management_system.entity.MyAppUser;
import com.sms.student_management_system.repository.MyAppUserRepository;
import com.sms.student_management_system.service.MyAppUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/myAppUser")
public class MyAppUserController {

    private final MyAppUserRepository myAppUserRepository;
    private final MyAppUserService myAppUserService;


    public MyAppUserController(MyAppUserService service,
                               MyAppUserRepository repository) {
        this.myAppUserRepository = repository;
        this.myAppUserService = service;
    }

    @GetMapping("/edit")
    public String getEditView(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("myAppUser", myAppUserService.loadCurrentUser(userDetails));
        return "myAppUserUpdate";
    }

    @PostMapping("/update")
    public String updateAuthenticatedUser(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("myAppUser") MyAppUser myAppUser, Model model) {
        try {
            MyAppUser user = myAppUserService.loadCurrentUser(userDetails);
            myAppUserService.updateUser(user, myAppUser);

            UserDetails updatedUserDetails = myAppUserService.loadUserByUsername(user.getUsername());

            Authentication newAuth = new UsernamePasswordAuthenticationToken(
                    updatedUserDetails,
                    updatedUserDetails.getPassword(),
                    updatedUserDetails.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(newAuth);

            model.addAttribute("message", "The user information is updated");
            model.addAttribute("status", "success");
            model.addAttribute("myAppUser", user);
            return "myAppUserUpdate";
        } catch(Exception e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("status", "error");
            model.addAttribute("myAppUser", myAppUserService.loadCurrentUser(userDetails));
            return "myAppUserUpdate";
        }
    }


    /**
     * This method makes the logout fully working and usable. If one logs out this method deletes the authentication
     * moreover it invalidates the user.
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            authentication.setAuthenticated(false);
            SecurityContextHolder.clearContext();
            request.getSession().invalidate();
        }

        return "redirect:/login";
    }




}
