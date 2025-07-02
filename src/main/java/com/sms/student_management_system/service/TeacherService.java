package com.sms.student_management_system.service;

import com.sms.student_management_system.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TeacherService {
    /**
     * Returns all the teachers in the database.
     * @return Teacher objects in the MySql database.
     */
    List<Teacher> getAllTeachers();

    /**
     * Saves the Teacher object provided as argument in the database.
     * @param teacher
     * @return saved teacher object
     */
    Teacher saveTeacher(Teacher teacher);

    /**
     * Deletes the Teacher instance under the given id.
     * @param id of the stored object
     * @return the deleted object
     */
    Teacher deleteTeacher(long id);

    /**
     * Update method for the object carrying the given id using the information of the given student object.
     * @param id -> id of the object that's being updated.
     * @param teacher -> object by which the object carrying the given id is updated.
     * @return newly updated object.
     */
    Teacher updateTeacher(long id, Teacher teacher);

    /**
     * Returns Teacher object corresponding to the given id.
     * @param id -> id of the searched object.
     * @return object corresponding to the given id.
     */
    Teacher getTeacherById(long id);
}
