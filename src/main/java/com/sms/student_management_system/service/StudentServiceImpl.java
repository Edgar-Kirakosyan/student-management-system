package com.sms.student_management_system.service;

import com.sms.student_management_system.entity.Student;
import com.sms.student_management_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    /**
     * A constructor that, using Spring, autowires the StudentRepository to the variable studentRepository.
     * @param sr StudentRepository type automatically given using Spring Beans.
     */
    public StudentServiceImpl (StudentRepository sr) {
        this.studentRepository = sr;
    }

    /**
     * Method for obtaining all students.
     * @return
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Method for saving the newly created student.
     * @param student
     * @return
     */
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.delete(studentRepository.getReferenceById(id));
    }

    public Student updateStudent(long id, Student student) {
        Student existingStudent = studentRepository.getReferenceById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        return saveStudent(existingStudent);
    }

    public Student getStudentById(long id) {
        return studentRepository.findById(id).get();
    }
}
