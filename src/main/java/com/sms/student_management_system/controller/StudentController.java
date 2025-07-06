package com.sms.student_management_system.controller;

import com.sms.student_management_system.entity.MyAppUser;
import com.sms.student_management_system.entity.Role;
import com.sms.student_management_system.entity.Student;
import com.sms.student_management_system.repository.MyAppUserRepository;
import com.sms.student_management_system.service.StudentService;
import com.sms.student_management_system.service.StudentServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final MyAppUserRepository myAppUserRepository;

    /**
     * A constructor for assigning a value for studentService instance variable.
     * @param ss
     */
    public StudentController(StudentService ss, MyAppUserRepository myAppUserRepository) {
        this.studentService = ss;
        this.myAppUserRepository = myAppUserRepository;
    }

    @GetMapping
    public String listStudents(Model model, @AuthenticationPrincipal UserDetails user){
        Optional<MyAppUser> optional = myAppUserRepository.findByUsername(user.getUsername());
        MyAppUser userOriginal = optional.get();

        if(userOriginal.getRole().equals(Role.TEACHER))
            return"redirect:/404";

        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/new")
    public String createStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    @PostMapping
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String deleteStudent(@PathVariable("id") long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/{id}")
    public String updateStudent(@PathVariable("id") long id,
                                @ModelAttribute("student")  Student student,
                                Model model) {
        model.addAttribute("student", studentService.updateStudent(id, student));
        return "redirect:/students";
    }

}
