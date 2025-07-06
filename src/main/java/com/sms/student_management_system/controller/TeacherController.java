package com.sms.student_management_system.controller;

import com.sms.student_management_system.entity.MyAppUser;
import com.sms.student_management_system.entity.Role;
import com.sms.student_management_system.entity.Student;
import com.sms.student_management_system.entity.Teacher;
import com.sms.student_management_system.repository.MyAppUserRepository;
import com.sms.student_management_system.service.MyAppUserService;
import com.sms.student_management_system.service.TeacherService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TeacherController {
        private final TeacherService teacherService;
        private final MyAppUserRepository myAppUserRepository;

        public TeacherController(TeacherService teacherService, MyAppUserRepository myAppUserRepository) {
            this.teacherService = teacherService;
            this.myAppUserRepository = myAppUserRepository;

        }

        @GetMapping("/teachers")
        public String listTeachers(Model model, @AuthenticationPrincipal UserDetails user) {
            Optional<MyAppUser> myAppUser = myAppUserRepository.findByUsername(user.getUsername());
            MyAppUser userOriginal = myAppUser.get();

            if(userOriginal.getRole().equals(Role.STUDENT))
                return "redirect:/404";

            model.addAttribute("teachers", teacherService.getAllTeachers());
            return "teachers";
        }

        @GetMapping("/teachers/new")
        public String createStudentForm(Model model) {
            Teacher teacher = new Teacher();
            model.addAttribute("teacher", teacher);
            return "create_teacher";
        }

        @PostMapping("/teachers")
        public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
            teacherService.saveTeacher(teacher);
            return "redirect:/teachers";
        }

        @GetMapping("/teachers/{id}")
        public String deleteTeacher(@PathVariable("id") long id) {
            teacherService.deleteTeacher(id);
            return "redirect:/teachers";
        }

        @GetMapping("/teachers/edit/{id}")
        public String editTeacherForm(@PathVariable("id") long id, Model model) {
            model.addAttribute("teacher", teacherService.getTeacherById(id));
            return "edit_teacher";
        }

        @PostMapping("/teachers/{id}")
        public String updateTeacher(@PathVariable("id") long id,
                                    @ModelAttribute("teacher") Teacher teacher,
                                    Model model) {
            model.addAttribute("teacher", teacherService.updateTeacher(id, teacher));
            return "redirect:/teachers";
        }
}
