package com.sms.student_management_system.controller;

import com.sms.student_management_system.entity.Student;
import com.sms.student_management_system.entity.Teacher;
import com.sms.student_management_system.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
        private final TeacherService teacherService;

        public TeacherController(TeacherService teacherService) {
            this.teacherService = teacherService;
        }

        @GetMapping
        public String listTeachers(Model model) {
            model.addAttribute("teachers", teacherService.getAllTeachers());
            return "teachers";
        }

        @GetMapping("/new")
        public String createStudentForm(Model model) {
            Teacher teacher = new Teacher();
            model.addAttribute("teacher", teacher);
            return "create_teacher";
        }

        @PostMapping
        public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
            teacherService.saveTeacher(teacher);
            return "redirect:/teachers";
        }

        @GetMapping("/{id}")
        public String deleteTeacher(@PathVariable("{id}") long id) {
            teacherService.deleteTeacher(id);
            return "redirect:/teachers";
        }

        @GetMapping("/edit/{id}")
        public String editTeacherForm(@PathVariable("id") long id, Model model) {
            model.addAttribute("teacher", teacherService.getTeacherById(id));
            return "edit_teacher";
        }

        @PostMapping("/{id}")
        public String updateTeacher(@PathVariable("id") long id,
                                    @ModelAttribute("teacher") Teacher teacher,
                                    Model model) {
            model.addAttribute("teacher", teacherService.updateTeacher(id, teacher));
            return "redirect:/teachers";
        }
}
