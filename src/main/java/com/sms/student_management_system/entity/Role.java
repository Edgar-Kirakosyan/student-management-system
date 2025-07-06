package com.sms.student_management_system.entity;

/**
 * An enum for the roles of the user.
 */
public enum Role {
    ADMIN("ADMIN"),
    STUDENT("STUDENT"),
    TEACHER("TEACHER");

    String label;

    Role(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

