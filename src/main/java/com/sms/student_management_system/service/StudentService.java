package com.sms.student_management_system.service;

import com.sms.student_management_system.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);

    void deleteStudent(long id);

    Student updateStudent(long id, Student student);

    Student getStudentById(long id);
}
