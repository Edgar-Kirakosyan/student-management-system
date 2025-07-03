package com.sms.student_management_system.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

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
        this.firstName = name;
        this.lastName = surname;
        this.email = email;
    }

    //Getter-Setters till the end of the class.
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
