package com.sms.student_management_system.service;

import com.sms.student_management_system.entity.Teacher;
import com.sms.student_management_system.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{

    private final TeacherRepository teacherRepository;

    /**
     * Constructor used for Spring to assign the TeacherRepository bean to the object teacherRepository.
     * @param tr -> TeacherRepository object given by Spring.
     */
    public TeacherServiceImpl(TeacherRepository tr) {
        this.teacherRepository = tr;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher deleteTeacher(long id) {
        Teacher teacher = getTeacherById(id);
        teacherRepository.delete(teacher);
        return teacher;
    }

    @Override
    public Teacher updateTeacher(long id, Teacher teacher) {
        Teacher teacherLocal = getTeacherById(id);
        teacherLocal.setId(id);
        teacherLocal.setEmail(teacher.getEmail());
        teacherLocal.setName(teacher.getName());
        teacherLocal.setSurname(teacher.getSurname());
        return saveTeacher(teacherLocal);
    }

    @Override
    public Teacher getTeacherById(long id) {
        return teacherRepository.findById(id).get();
    }
}
