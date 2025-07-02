package com.sms.student_management_system.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    /**
     * Default constructor.
     */
    public Teacher(){}

    /**
     * Constructor with parameters as arguments.
     * @param name
     * @param surname
     * @param email
     */
    public Teacher(String name,
                   String surname,
                   String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    //Getter-Setters till the end of the class.
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
