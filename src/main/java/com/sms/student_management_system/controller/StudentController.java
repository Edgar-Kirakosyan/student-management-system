package com.sms.student_management_system.controller;

import com.sms.student_management_system.entity.Student;
import com.sms.student_management_system.service.StudentService;
import com.sms.student_management_system.service.StudentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private final StudentService studentService;

    /**
     * A constructor for assigning a value for studentService instance variable.
     * @param ss
     */
    public StudentController(StudentService ss) {
        this.studentService = ss;
    }

    @GetMapping("/students")
    public String listStudents(Model model){
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable("id") long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable("id") long id,
                                @ModelAttribute("student")  Student student,
                                Model model) {
        model.addAttribute("student", studentService.updateStudent(id, student));
        return "redirect:/students";
    }
}
