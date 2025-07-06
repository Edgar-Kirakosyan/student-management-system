package com.sms.student_management_system.exception;

/**
 * An Exception class for the case when the username is already registered, this is used for the database not to
 * have copies of the same username. The login page is based on the username search.
 */
public class UserIsAlreadyPresentException extends Exception{
    public UserIsAlreadyPresentException() {
        super("Username is already present.");
    }

    public UserIsAlreadyPresentException(String message) {
        super(message + " user is already registered, use another username.");
    }

    public UserIsAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsAlreadyPresentException(Throwable cause) {
        super(cause);
    }
}
